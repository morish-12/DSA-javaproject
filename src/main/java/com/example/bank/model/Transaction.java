package com.example.bank.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int fromAccount;
    private int toAccount;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int id, int fromAccount, int toAccount, double amount) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
