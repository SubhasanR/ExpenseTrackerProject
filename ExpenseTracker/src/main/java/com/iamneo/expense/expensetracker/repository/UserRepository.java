package com.iamneo.expense.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iamneo.expense.expensetracker.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUid(Long Uid);    
}
