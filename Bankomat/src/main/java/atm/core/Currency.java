package main.java.atm.core;



public enum Currency {
    USD, EUR, RUB;

    public static boolean isValidCurrency(String currency) {
        for (Currency c : values()) {
            if (c.name().equalsIgnoreCase(currency)) {
                return true;
            }
        }
        return false;
    }
}
