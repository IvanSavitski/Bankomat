package main.java.atm.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.atm.core.ATM;
import main.java.atm.core.ATMState;
import main.java.atm.core.Account;
import main.java.atm.core.DataManager;



public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        DataManager.loadAccounts(accounts);
        ATMState atmState = new ATMState();
        ATM atm = new ATM(accounts, atmState);
        Menu menu = new Menu(atm);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu.displayMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    menu.authenticateUser(scanner);
                    break;
                case "2":
                    menu.performCheckBalance();
                    break;
                case "3":
                    menu.performWithdraw(scanner);
                    break;
                case "4":
                    menu.performDeposit(scanner);
                    break;
                case "5":
                    menu.performPrintTransactionHistory();
                    break;
                case "6":
                    DataManager.saveAccounts(accounts);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
