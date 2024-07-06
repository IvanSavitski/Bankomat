package main.java.atm.ui;



import java.util.Scanner;

import main.java.atm.core.ATM;
import main.java.atm.core.ATMException;



public class Menu {
    private ATM atm;

    public Menu(ATM atm) {
        this.atm = atm;
    }

    public void displayMainMenu() {
        System.out.println("ATM Main Menu:");
        System.out.println("1. Login");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw");
        System.out.println("4. Deposit");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public void authenticateUser(Scanner scanner) {
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        try {
            atm.authenticate(cardNumber, pin);
            System.out.println("Authentication successful.");
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }

    public void performCheckBalance() {
        try {
            double balance = atm.checkBalance();
            System.out.println("Current balance: " + balance);
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }

    public void performWithdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            atm.withdraw(amount);
            System.out.println("Withdrawal successful.");
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }

    public void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            atm.deposit(amount);
            System.out.println("Deposit successful.");
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }

    public void performPrintTransactionHistory() {
        try {
            atm.printTransactionHistory();
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }
}

