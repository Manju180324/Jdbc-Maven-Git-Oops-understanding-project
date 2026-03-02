package org.bank.transaction;

import java.time.LocalDateTime;

public class Transaction {

    private int transac_id;
    private int fromAccount;
    private int toAccount;
    private double amount;
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(int fromAccount, int toAccount, double amount, LocalDateTime transactionDate) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters

    public int getId() {
        return transac_id;
    }

    public void setId(int transac_id) {
        this.transac_id = transac_id;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}