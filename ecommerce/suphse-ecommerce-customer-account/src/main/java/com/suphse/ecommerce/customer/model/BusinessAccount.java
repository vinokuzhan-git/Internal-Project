package com.suphse.ecommerce.customer.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class BusinessAccount {

    @NotNull(message = "bisAccountId is mandatory")
    private UUID bisAccountId;

    @NotEmpty(message = "accounts are mandatory")
    private List<Account> accounts;

    // Getters and Setters

    public UUID getBisAccountId() {
        return bisAccountId;
    }

    public void setBisAccountId(UUID bisAccountId) {
        this.bisAccountId = bisAccountId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    // Inner Account class
    public static class Account {

        @NotNull(message = "accountId is mandatory")
        private UUID accountId;

        public UUID getAccountId() {
            return accountId;
        }

        public void setAccountId(UUID accountId) {
            this.accountId = accountId;
        }
    }
}
