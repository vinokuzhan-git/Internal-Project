package com.suphse.ecommerce.customer.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suphse.ecommerce.customer.config.AccountType;
import com.suphse.ecommerce.customer.config.Action;
import com.suphse.ecommerce.customer.dao.AccountDetailsDao;
import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.exception.BusinessValidationException;
import com.suphse.ecommerce.customer.exception.CustomNotFoundException;
import com.suphse.ecommerce.customer.exception.InternalServerException;
import com.suphse.ecommerce.customer.model.AccountUpgrade;
import com.suphse.ecommerce.customer.service.AccountDetailsService;

import jakarta.transaction.Transactional;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountDetailsDao accountdetailsdao;

	@Override
	@Transactional
	public AccountDetails saveAccountDetails(AccountDetails ad) {
		// TODO Auto-generated method stub
		try {
			return accountdetailsdao.saveAccountDetails(ad);
		} catch (BusinessValidationException ex) {
			log.error("BusinessValidationException exception occured !!");
			throw ex;
		} catch (Exception ex) {
			log.error("Generic exception occured !!");
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}

	@Override
	public AccountDetails fetchAccountDetails(UUID accountId) {
		// TODO Auto-generated method stub
		try {
			return accountdetailsdao.fetchAccountDetailsById(accountId);
		} catch (CustomNotFoundException ex) {
			log.error("CustomNotFoundException exception occured !!");
			throw ex;
		} catch (Exception ex) {
			log.error("Generic exception occured :: Error : "+ex);
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}

	@Override
	@Transactional
	public AccountDetails updateAccountDetails(AccountDetails ad) {
		try {
			AccountDetails oldad = null;
			if (!(ad.getId() == null))
				oldad = accountdetailsdao.fetchAccountDetailsByEmailAndId(ad.getEmail(), ad.getId());
			else
				oldad = accountdetailsdao.fetchAccountDetailsByEmail(ad.getEmail());
			// TODO Auto-generated method stub
			if (oldad != null) {
				ad.setId(oldad.getId());
				ad.setAccountType(oldad.getAccountType());
				return accountdetailsdao.updateAccountDetails(ad);
			}
		} catch (CustomNotFoundException ex) {
			log.error("CustomNotFoundException exception occured !!");
			throw ex;
		} catch (Exception ex) {
			log.error("Generic exception occured :: Error : "+ex);
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}

		return null;
	}

	@Override
	@Transactional
	public AccountDetails upgradeAccount(UUID accountId) {
		try {
			int ordercount = accountdetailsdao.countOrderByAccountId(accountId);
			if(ordercount < 10)
				throw new BusinessValidationException("This account is not eligible for Upgrade !!");
			AccountType actTyp = AccountType.getByKey(1);
			AccountDetails ad = accountdetailsdao.fetchAccountDetailsById(accountId);
			ad.setAccountType(actTyp.getValue());
			return accountdetailsdao.updateAccountDetails(ad);
		} catch (CustomNotFoundException ex) {
			log.error("CustomNotFoundException exception occured !!");
			throw ex;
		} 
		catch (BusinessValidationException ex) {
			log.error("BusinessValidationException exception occured !!");
			throw ex;
		} 
		catch (Exception ex) {
			log.error("Generic exception occured !!");
			throw new InternalServerException("An unexpected error occurred. Please try again later");
		}
	}

}
