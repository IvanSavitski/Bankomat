package main.java.atm.core;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;



public class ATM {
    private List<Account> accounts;
    private Account currentAccount;
    private ATMState atmState;
    private static final Logger LOGGER = Logger.getLogger(ATM.class.getName());

    public ATM(List<Account> accounts, ATMState atmState) {
        this.accounts = accounts;
        this.atmState = atmState;
        this.atmState.initializeATM();
    }

    public void authenticate(String cardNumber, String pin) throws ATMException {
        for (Account account : accounts) {
            if (account.getCardNumber().equals(cardNumber)) {
                if (account.isLocked() && Duration.between(account.getLockTime(), LocalDateTime.now()).toDays() < 1) {
                    throw new ATMException("Card is locked. Try again later.");
                } else if (account.isLocked()) {
                    account.unlockAccount();
                }
                if (account.getPin().equals(pin)) {
                    currentAccount = account;
                    account.resetPinAttempts();
                    LOGGER.info("Authentication successful for card: " + cardNumber);
                    return;
                } else {
                    account.incrementPinAttempts();
                    if (account.getPinAttempts() >= 3) {
                        account.lockAccount();
                        LOGGER.warning("Card locked due to too many incorrect PIN attempts: " + cardNumber);
                        throw new ATMException("Card is locked due to too many incorrect PIN attempts.");
                    } else {
                        throw new ATMException("Incorrect PIN.");
                    }
                }
            }
        }
        throw new ATMException("Card number not found.");
    }

    public double checkBalance() throws ATMException {
        ensureAuthenticated();
        return currentAccount.getBalance();
    }

    public void withdraw(double amount) throws ATMException {
        ensureAuthenticated();
        if (amount > currentAccount.getBalance()) {
            throw new ATMException("Insufficient funds.");
        } else if (amount > atmState.getAtmBalance(currentAccount.getCurrency())) {
            throw new ATMException("ATM balance limit exceeded.");
        } else {
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            atmState.updateAtmBalance(currentAccount.getCurrency(), -amount);
            TransactionManager.recordTransaction(currentAccount, "Withdraw", amount, currentAccount.getCurrency());
            LOGGER.info("Withdrawal of " + amount + " " + currentAccount.getCurrency() + " from card: " + currentAccount.getCardNumber());
        }
    }

    public void deposit(double amount) throws ATMException {
        ensureAuthenticated();
        if (amount > atmState.getMaxDepositAmount()) {
            throw new ATMException("Deposit amount exceeds ATM balance limit.");
        } else {
            currentAccount.setBalance(currentAccount.getBalance() + amount);
            atmState.updateAtmBalance(currentAccount.getCurrency(), amount);
            TransactionManager.recordTransaction(currentAccount, "Deposit", amount, currentAccount.getCurrency());
            LOGGER.info("Deposit of " + amount + " " + currentAccount.getCurrency() + " to card: " + currentAccount.getCardNumber());
        }
    }

    public void printTransactionHistory() throws ATMException {
        ensureAuthenticated();
        TransactionManager.printTransactionHistory(currentAccount);
    }

    private void ensureAuthenticated() throws ATMException {
        if (currentAccount == null) {
            throw new ATMException("User not authenticated.");
        }
    }
}

