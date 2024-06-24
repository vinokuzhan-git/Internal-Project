package com.suphse.ecommerce.customer.config;

public enum AccountType {
    BUSINESS_ACCOUNT(1, "Business Account"),
    SUB_ACCOUNT(2, "subAccount"),
    PERSONAL_ACCOUNT(3, "Personal Account");

    private final int key;
    private final String value;

    // Constructor
    AccountType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getter for key
    public int getKey() {
        return key;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Override toString method
    @Override
    public String toString() {
        return key + " - " + value;
    }

    // Static method to get enum by key
    public static AccountType getByKey(int key) {
        for (AccountType accountType : values()) {
            if (accountType.key == key) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Invalid key: " + key);
    }

    // Static method to get enum by value
    public static AccountType getByValue(String value) {
        for (AccountType accountType : values()) {
            if (accountType.value.equalsIgnoreCase(value)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}

