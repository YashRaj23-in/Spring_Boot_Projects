package com.example.BorrowBuddy.Model;

import jakarta.persistence.*;

@Entity
public class BorrowRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="item_id",nullable = false)
    private Item item;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User borrower;
    @Column(nullable = false)
    private String status="PENDING";
    public BorrowRequest(){}

    public User getBorrower() {
        return borrower;
    }

    public Integer getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
