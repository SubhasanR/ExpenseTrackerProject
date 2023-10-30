package com.expense.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "user_login")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="username")
    private String username;


}
