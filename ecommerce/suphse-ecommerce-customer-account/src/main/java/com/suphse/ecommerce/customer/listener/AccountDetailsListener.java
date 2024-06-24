package com.suphse.ecommerce.customer.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.suphse.ecommerce.customer.config.AccountType;
import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.entity.manager.EntityManagerProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PreUpdate;

public class AccountDetailsListener {

	 @Autowired
	 private EntityManagerProvider entityManagerProvider;

	    @PreUpdate
	    public void preUpdate(AccountDetails accountDetails) {
	        EntityManager entityManager = entityManagerProvider.getEntityManager();
	        AccountDetails existingAccountDetails = entityManager.find(AccountDetails.class, accountDetails.getId());
	        if (existingAccountDetails != null && AccountType.PERSONAL_ACCOUNT.getValue().equals(accountDetails.getAccountType())) {
	            accountDetails.setAccountType(existingAccountDetails.getAccountType());
	        }
	    }
}
