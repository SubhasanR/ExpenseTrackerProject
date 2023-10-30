package com.expense.utils;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Component
public class HibernateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    HibernateUtil(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
        HibernateUtil.sessionFactory = sessionFactory;
        HibernateUtil.jdbcTemplate = jdbcTemplate;
    }

    public static <T> List<T> runNativeQuery(String queryString, Map<String, Object> parametersToSet, Class<T> className) {
        Session session = null;
        TypedQuery<T> query;
        List<T> list = null;
        try {
            session = sessionFactory.openSession();
            query = session.createNativeQuery(queryString, className);

            if (parametersToSet != null && !parametersToSet.isEmpty()) {
                parametersToSet.forEach(query::setParameter);
            }

            list = query.getResultList();
            if (list != null && list.size() == 0) {
                list = null;
            }
        } catch (Exception e) {
            LOGGER.error("Exception in runQuery() {}: {}", className.getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public static Map<String, Map<String, Object>> runNativeQueryForMap(String nativeQueryStr,
                                                                        Set<String> keyColumnValueSet) {

        Map<String, Map<String, Object>> resultMap = new HashMap<>();
/*        ModelMapper modelMapper = new ModelMapper();
        // mapping this way works better? but heavily error-prone better switch to STRICT
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);*/

        try {
            resultMap = jdbcTemplate.query(nativeQueryStr, (rs) -> {
                if (!rs.isBeforeFirst()) {
                    LOGGER.error("Query returned no rows");
                    return null;
                }

                Map<String, Map<String, Object>> rowMap = new HashMap<>();

                while (rs.next()) {
                    Map<String, Object> fetchedMap = new HashMap<>();
                    StringJoiner key = new StringJoiner("_");

                    for (String s : keyColumnValueSet) {
                        key.add(rs.getString(s));
                    }

                    for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                        fetchedMap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }

                    rowMap.put(key.toString(), fetchedMap);
                }
                return rowMap;
            });
        } catch (Exception e) {
            LOGGER.error("Error in runQueryForMap(): ", e);
        }
        return resultMap;
    }

    public static <T> boolean saveOrUpdate(T classObj) {
        boolean success = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(classObj);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("Exception in saveOrUpdate() {} : {}", classObj.getClass().getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }

    public static <T> boolean saveOrUpdateList(List<T> objList) {
        boolean success = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (CollectionUtils.isEmpty(objList)) {
                return false;
            }

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            for (T t : objList) {
                session.saveOrUpdate(t);
            }

            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("Exception in saveOrUpdateList() {} : {}", objList.getClass().getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }

    public static <T> T getSingleResult(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static <T> T getSingleResult(String queryString, Map<String, Object> parametersToSet, Class<T> className) {
        return getSingleResult(runQuery(queryString, parametersToSet, className, 1, 0));
    }

    @Deprecated
    public static <T> List<T> runQueryWithLimitAndOffSet(String queryString, Map<String, Object> parametersToSet, int limit, int offset, Class<T> className) {
        Session session = null;
        TypedQuery<T> query;
        List<T> list = null;
        try {
            session = sessionFactory.openSession();
            query = session.createQuery(queryString, className);

            if (parametersToSet != null && !parametersToSet.isEmpty()) {
                parametersToSet.forEach(query::setParameter);
            }
            query.setMaxResults(limit);
            query.setFirstResult(offset);
            list = query.getResultList();
            if (list != null && list.size() == 0) {
                list = null;
            }
        } catch (Exception e) {
            LOGGER.error("Exception in runQuery() {}: {}", className.getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public static boolean runQueryForUpdateOrDelete(String queryString, Map<String, Object> parametersToSet) {
        boolean success = false;
        Session session = null;
        Transaction tx = null;
        Query query;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            query = session.createQuery(queryString);
            if (parametersToSet != null && !parametersToSet.isEmpty()) {
                parametersToSet.forEach(query::setParameter);
            }
            query.executeUpdate();
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("Error in runQueryForUpdateOrDelete : ", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }

    public static Optional<Long> getIntegerForCountQuery(String query) {
        try {
            return Optional.ofNullable(getSingleResult(query, null, Long.class));
        } catch (Exception e) {
            LOGGER.error("Error in getIntegerForCountQuery(): ", e);
            return Optional.empty();
        }
    }

    private static <T> List<T> runQueryHelper(String queryString, Map<String, Object> parametersToSet,
                                              Class<T> className, Integer limit, Integer offset) {
        List<T> list = null;
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<T> query = session.createQuery(queryString, className);

            if (parametersToSet != null && !parametersToSet.isEmpty()) {
                parametersToSet.forEach(query::setParameter);
            }

            if (limit != null && offset != null) {
                query.setMaxResults(limit);
                query.setFirstResult(offset);
            }

            list = query.getResultList(); // this never returns null;
            if (list != null && list.size() == 0) {
                list = null;
            }
        } catch (Exception e) {
            LOGGER.error("Exception in runQuery() {}: ", className.getSimpleName(), e);
        }
        return list;
    }

    // delete an object without query
    public static <T> boolean delete(T classObj) {
        boolean success = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(classObj);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("Exception in delete() {} : {}", classObj.getClass().getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }


    public static <T> List<T> runQuery(String queryString, Map<String, Object> parametersToSet, Class<T> className) {
        return runQueryHelper(queryString, parametersToSet, className, null, null);
    }

    public static <T> List<T> runQuery(String queryString, Map<String, Object> parametersToSet, Class<T> className, Integer limit, Integer offset) {
        return runQueryHelper(queryString, parametersToSet, className, limit, offset);
    }
}
