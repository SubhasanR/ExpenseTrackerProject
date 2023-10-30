package com.expense.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dao.UserDao;
import com.expense.dto.UsersDto;
import com.expense.model.Users;


//@Service
//public class UsersServiceImpl implements UsersService {
//	
//	@Autowired
//	private UsersRepository usersRepository;
//
//	Users users=new Users();
//	@Override
//	public Users addExpense(UsersDto dto) {
//		BeanUtils.copyProperties(dto, users);
//		usersRepository.save(users);
//		return users;
//	}
//
//	@Override
//	public boolean deleteExpense(Long expenseId) {
//		Optional<Users> findById = usersRepository.findById(expenseId);
//		usersRepository.deleteById(expenseId);
//		return false;
//	}
//
//}


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserDao usersRepository;

    @Override
    public Users addExpense(UsersDto dto) {
        Users users = new Users(); // Create a new Users object for each request
        BeanUtils.copyProperties(dto, users);
        boolean added = usersRepository.save(users);
        if(added)
            return users;
        else
            return null;// Save the new Users object
    }

    @Override
    public boolean deleteExpense(Long expenseId) {
        if (usersRepository.existsById(expenseId)) {
            usersRepository.deleteById(expenseId);
            return true;
        }
        return false;
    }

}

