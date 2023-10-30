package com.expense.dao;


import com.expense.model.Login;
import com.expense.utils.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDao {

    public Login findLoginByEmail(String email) {
        String queryString = "from Login where email = :EMAIL";
        Map<String, Object> parametersToSet = new HashMap<>();
        parametersToSet.put("EMAIL", email);
        return HibernateUtil.getSingleResult(queryString, parametersToSet, Login.class);
    }

    public boolean save(Login n) {
         return HibernateUtil.saveOrUpdate(n);
    }

    public List<Login> findAllLogin() {
        String queryString = "FROM Login";
        return HibernateUtil.runQuery(queryString, null, Login.class);
    }
}
