package main.java.atm.core;


import java.util.HashMap;
import java.util.Map;


public class ATMState {
    private static final double MAX_DEPOSIT_AMOUNT = 1000000.0;
    private Map<String, Double> atmBalances;

    public ATMState() {
        atmBalances = new HashMap<>();
    }

    public void initializeATM() {
        atmBalances.put("USD", 50000.0);
        atmBalances.put("EUR", 40000.0);
        atmBalances.put("RUB", 3000000.0);
    }

    public double getAtmBalance(String currency) {
        return atmBalances.getOrDefault(currency, 0.0);
    }

    public void updateAtmBalance(String currency, double amount) {
        atmBalances.put(currency, getAtmBalance(currency) + amount);
    }

    public double getMaxDepositAmount() {
        return MAX_DEPOSIT_AMOUNT;
    }
}
