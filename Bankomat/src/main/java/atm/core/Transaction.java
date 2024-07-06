package main.java.atm.core;

import java.time.LocalDateTime;



public class Transaction {
    private LocalDateTime date;
    private String type;
    private double amount;
    private String currency;

    public Transaction(String type, double amount, String currency) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.currency = currency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return date + " " + type + " " + amount + " " + currency;
    }
}
