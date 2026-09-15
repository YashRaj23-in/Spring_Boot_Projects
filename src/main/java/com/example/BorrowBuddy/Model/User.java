package com.example.BorrowBuddy.Model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private double reputationscore=5.0;

    public User(){}
    public User(String email,String name) {
        this.email = email;
        this.name = name;
        this.reputationscore =5.0;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getReputationscore() {
        return reputationscore;
    }
}
