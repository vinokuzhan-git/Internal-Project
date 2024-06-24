package com.suphse.ecommerce.customer.config;

public enum Action {
    ACCEPT("ACCEPT"),
    REJECT("REJECT"),
    UNLINK("UNLINK");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Action fromValue(String value) {
        for (Action action : Action.values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown Action: " + value);
    }
}


