package com.suphse.ecommerce.customer.model;

import java.util.UUID;

public class AccountUpgrade {
	
	private UUID accountId;
	private String action;
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
	@Override
	public String toString() {
		return "AccountUpgrade [accountId=" + accountId + ", action=" + action + "]";
	}
	
	

}
