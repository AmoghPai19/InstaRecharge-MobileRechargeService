package com.MobileRecharge.Main;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;


    @Column(name = "user_id", nullable = false)
    private String userId;

    private String name;
    private String password;

    @Column(name = "wallet_balance")
    private double walletBalance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    public User() {}

    public User(String userId, String name, double walletBalance, String password) {
        this.userId = userId;
        this.name = name;
        this.walletBalance = walletBalance;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public double getWalletBalance() { return walletBalance; }
    public String getPassword() { return password; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
    public void setPassword(String password) { this.password = password; }

    public List<Transaction> getTransactionHistory() { return transactions; }

    public void addBalance(double amount) {
        if (amount > 0) walletBalance += amount;
    }

    public boolean deductBalance(double amount) {
        if (amount <= walletBalance) {
            walletBalance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}