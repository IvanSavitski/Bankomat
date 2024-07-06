package main.java.atm.core;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class DataManager {
    private static final String FILE_PATH = "C:\\$$Java_VS_CODE\\Bankomat\\Bankomat\\src\\main\\resources\\accounts.txt";

    public static void loadAccounts(List<Account> accounts) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4) {
                    String cardNumber = parts[0];
                    String pin = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    String currency = parts[3];
                    accounts.add(new Account(cardNumber, pin, balance, currency));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts file: " + e.getMessage());
        }
    }
	

    public static void saveAccounts(List<Account> accounts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account account : accounts) {
                bw.write(account.getCardNumber() + " " + account.getPin() + " " + account.getBalance() + " " + account.getCurrency());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to accounts file: " + e.getMessage());
        }
    }
}

