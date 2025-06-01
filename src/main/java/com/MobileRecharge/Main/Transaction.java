package com.MobileRecharge.Main;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String planDescription;

    private String description;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Transaction() {}

    public Transaction(String planDescription, double amount) {
        this.planDescription = planDescription;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public void display() {
        System.out.println("Transaction: " + planDescription);
        System.out.println("₹" + amount);
        System.out.println(timestamp);
    }
}
