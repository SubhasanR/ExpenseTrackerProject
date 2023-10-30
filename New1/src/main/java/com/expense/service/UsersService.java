package com.expense.service;

import java.util.List;

import com.expense.dto.UsersDto;
import com.expense.model.Users;

public interface UsersService {

	Users addExpense(UsersDto dto);

	boolean deleteExpense(Long expenseId);
	
	// public List<Users> getAllExpenses();

}
