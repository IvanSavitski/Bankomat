package main.java.atm.core;



public class CardValidator {
    private static final String CARD_REGEX = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches(CARD_REGEX);
    }

    public static boolean isValidPin(String pin) {
        return pin.matches("\\d{4}");
    }
}

