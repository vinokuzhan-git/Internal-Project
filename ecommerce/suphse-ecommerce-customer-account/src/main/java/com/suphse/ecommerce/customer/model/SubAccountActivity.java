package com.suphse.ecommerce.customer.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.util.UUID;

import com.suphse.ecommerce.customer.config.Action;

public class SubAccountActivity {

    @NotNull(message = "bisAccountId is mandatory")
    private UUID bisAccountId;

    @NotNull(message = "accountId is mandatory")
    private UUID accountId;

    @NotNull(message = "action is mandatory")
    private String action;

    
    
    public SubAccountActivity(@NotNull(message = "bisAccountId is mandatory") UUID bisAccountId,
			@NotNull(message = "accountId is mandatory") UUID accountId,
			@NotNull(message = "action is mandatory") @Min(value = 0, message = "action must be a non-negative integer") String action) {
		super();
		this.bisAccountId = bisAccountId;
		this.accountId = accountId;
		this.action = action;
	}

	// Getters and Setters
    

    public UUID getBisAccountId() {
        return bisAccountId;
    }

    public void setBisAccountId(UUID bisAccountId) {
        this.bisAccountId = bisAccountId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
