package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.UsersDto;
import com.expense.model.Users;
import com.expense.service.UsersService;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {

    @Autowired
    private UsersService expenseService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody UsersDto expense) {
        System.out.println(expense);
        Users user = expenseService.addExpense(expense);
        if (user != null)
            return ResponseEntity.ok("Expense Added");
        else
            return new ResponseEntity<>("Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {
            return ResponseEntity.ok("Expense Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }

    // @GetMapping("/allexpenses")
    // public ResponseEntity<List<Users>> getAllExpenses() {
    //     List<Users> expenses = expenseService.getAllExpenses(); // Assuming you have a service method to retrieve all expenses
    //     if (expenses != null && !expenses.isEmpty()) {
    //         return new ResponseEntity<>(expenses, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    
}
