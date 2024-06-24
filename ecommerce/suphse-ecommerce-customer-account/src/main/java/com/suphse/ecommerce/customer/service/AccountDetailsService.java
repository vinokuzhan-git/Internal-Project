package com.suphse.ecommerce.customer.service;

import java.util.UUID;

import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.model.AccountUpgrade;

public interface AccountDetailsService {
	
    public AccountDetails saveAccountDetails(AccountDetails ad);
    
    public AccountDetails updateAccountDetails(AccountDetails ad);

	public AccountDetails fetchAccountDetails(UUID accountId);

	AccountDetails upgradeAccount(UUID accountId);

}
