package com.expense.controller;

import com.expense.dao.LoginDao;
import com.expense.dao.UserDao;
import com.expense.model.Login;
import com.expense.utils.ConversionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class loginContoller {

    @Autowired
    private LoginDao userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<String> addNewUser(@RequestBody Login user) {
        Login existingUser = userRepository.findLoginByEmail(user.getEmail());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        } else {
            boolean added = userRepository.save(user);
            if(added)
            return ResponseEntity.status(HttpStatus.OK).body("User saved successfully");
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not saved");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Login user) {
        Login existingUser = userRepository.findLoginByEmail(user.getEmail());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<?> getAllUsers() {
        List<Login> loginList = userRepository.findAllLogin();
        if (CollectionUtils.isEmpty(loginList))
            return ResponseEntity.ok(ConversionUtils.setResponse("error", "No Data Found"));
        else
            return ResponseEntity.ok(ConversionUtils.setResponse("success", "All Data Found", "data", loginList));
    }
}
