package main.java.atm.core;


import java.util.List;



public class TransactionManager {
    public static void recordTransaction(Account account, String type, double amount, String currency) {
        Transaction transaction = new Transaction(type, amount, currency);
        account.addTransaction(transaction);
    }

    public static void printTransactionHistory(Account account) {
        List<Transaction> transactions = account.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}
