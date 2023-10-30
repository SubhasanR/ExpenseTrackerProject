package com.expense.dao;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.expense.model.Users;
import com.expense.utils.HibernateUtil;

@Repository
public class UserDao {

    public Users findUserByEmail(String email) {
        String queryString = "from Users where email = :EMAIL";
        Map<String, Object> parametersToSet = new HashMap<>();
        parametersToSet.put("EMAIL", email);
        return HibernateUtil.getSingleResult(queryString, parametersToSet, Users.class);
    }

    public boolean save(Users users) {
         return HibernateUtil.saveOrUpdate(users);
    }

    public boolean existsById(Long expenseId) {
        String queryString = "FROM Users WHERE id = :EXPENSE_ID";
        Map<String, Object> parametersToSet = new HashMap<>();
        parametersToSet.put("EXPENSE_ID", expenseId);
        return HibernateUtil.getSingleResult(queryString, parametersToSet, Users.class) != null;
    }

    public void deleteById(Long expenseId) {
        String queryString = "DELETE FROM Users WHERE id = :EXPENSE_ID";
        Map<String, Object> parametersToSet = new HashMap<>();
        parametersToSet.put("EXPENSE_ID", expenseId);
        HibernateUtil.runQuery(queryString, parametersToSet, Users.class);
    }
    
    
    // public List<Users> findAll() {
    //     String queryString = "FROM Users"; // Query to select all Users (expenses)
    //     return HibernateUtil.getResultList(queryString, Users.class);
    // }


}
