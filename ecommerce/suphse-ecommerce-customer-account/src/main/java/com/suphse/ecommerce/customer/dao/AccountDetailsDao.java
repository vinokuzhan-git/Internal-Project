package com.suphse.ecommerce.customer.dao;

import java.util.UUID;

import com.suphse.ecommerce.customer.entity.AccountDetails;

public interface AccountDetailsDao {
	
	public AccountDetails saveAccountDetails(AccountDetails ad);
	
	public AccountDetails fetchAccountDetailsByEmail(String accountId);

	public AccountDetails fetchAccountDetailsById(UUID accountId);

	public AccountDetails fetchAccountDetailsByEmailAndId(String email, UUID id);

	public AccountDetails updateAccountDetails(AccountDetails ad);

	public int countOrderByAccountId(UUID accountId);

	boolean validateAccountType(UUID accountId, String accountType);

}
