package com.suphse.ecommerce.customer.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import com.suphse.ecommerce.customer.dao.AccountDetailsDao;
import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.exception.BusinessValidationException;
import com.suphse.ecommerce.customer.exception.CustomNotFoundException;
import com.suphse.ecommerce.customer.exception.InternalServerException;
import com.suphse.ecommerce.customer.repository.AccountDetailsRepository;
import com.suphse.ecommerce.customer.repository.OrderDetailsRepository;

@Component
public class AccountDetailsDaoImpl implements AccountDetailsDao {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountDetailsRepository accountdetailsrepo;
	
	@Autowired
	private OrderDetailsRepository orderdetailsrepo;

	@Override
	public AccountDetails saveAccountDetails(AccountDetails ad) {
		try {
			AccountDetails oldad = accountdetailsrepo.findByEmail(ad.getEmail());
			if (oldad != null)
				throw new BusinessValidationException("Account Already Exist !!");
			return accountdetailsrepo.save(ad);
		} catch (BusinessValidationException ex) {
			log.error("BusinessValidationException exception occured !!");
			throw ex;
		}
		catch (DataAccessException ex) {
		    Throwable rootCause = ex.getRootCause();
		    log.error("DataAccessException :: Root cause: " + rootCause);
		    throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
		catch (Exception ex) {
			log.error("Generic exception occured !!");
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}
	
	@Override
	public AccountDetails updateAccountDetails(AccountDetails ad) {
		try {
			return accountdetailsrepo.save(ad);
		} 
		catch (DataAccessException ex) {
		    Throwable rootCause = ex.getRootCause();
		    log.error("DataAccessException :: Root cause: " + rootCause);
		    throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
		catch (Exception ex) {
			log.error("Generic exception occured :: Error : "+ex);
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}

	@Override
	public AccountDetails fetchAccountDetailsById(UUID accountId) {
		try
		{
		AccountDetails ad = accountdetailsrepo.findById(accountId).orElseGet(null);
		
		if(ad == null)
			throw new CustomNotFoundException("This entity not found");
		return ad;
		}
		catch (CustomNotFoundException ex) {
			log.error("CustomNotFoundException exception occured !!");
			throw ex;
		}
		catch (DataAccessException ex) {
		    Throwable rootCause = ex.getRootCause();
		    log.error("DataAccessException :: Root cause: " + rootCause);
		    throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
		catch (Exception ex) {
			log.error("Generic exception occured !!");
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}

	@Override
	public AccountDetails fetchAccountDetailsByEmail(String email) {
		AccountDetails ad = accountdetailsrepo.findByEmail(email);
		if(ad == null)
			throw new CustomNotFoundException("This entity not found");
		return ad;
	}

	@Override
	public AccountDetails fetchAccountDetailsByEmailAndId(String email, UUID id) {
		AccountDetails ad =  accountdetailsrepo.findByEmailAndId(email, id);
		if(ad == null)
			throw new CustomNotFoundException("This entity not found");
		return ad;
	}
	
	@Override
	public int countOrderByAccountId(UUID accountId)
	{
		return orderdetailsrepo.countOrdersByAccountId(accountId);
		
	}
	
	@Override
	public boolean validateAccountType(UUID accountId,String accountType)
	{
		return accountdetailsrepo.existsByIdAndAccountType(accountId, accountType);
		
	}

}
