package main.java.atm.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class Account {
    private String cardNumber;
    private String pin;
    private double balance;
    private String currency;
    private int pinAttempts;
    private LocalDateTime lockTime;
    private List<Transaction> transactions;

    public Account(String cardNumber, String pin, double balance, String currency) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.currency = currency;
        this.pinAttempts = 0;
        this.lockTime = null;
        this.transactions = new ArrayList<>();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPinAttempts() {
        return pinAttempts;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void incrementPinAttempts() {
        this.pinAttempts++;
    }

    public void resetPinAttempts() {
        this.pinAttempts = 0;
    }

    public void lockAccount() {
        this.lockTime = LocalDateTime.now();
    }

    public void unlockAccount() {
        this.lockTime = null;
        resetPinAttempts();
    }

    public boolean isLocked() {
        return lockTime != null;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
