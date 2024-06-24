package com.suphse.ecommerce.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suphse.ecommerce.customer.dao.AccountDetailsDao;
import com.suphse.ecommerce.customer.dao.InvitationDetailsDao;
import com.suphse.ecommerce.customer.entity.InvitationDetails;
import com.suphse.ecommerce.customer.exception.BusinessValidationException;
import com.suphse.ecommerce.customer.model.BusinessAccount;
import com.suphse.ecommerce.customer.model.BusinessAccount.Account;
import com.suphse.ecommerce.customer.repository.AccountDetailsRepository;
import com.suphse.ecommerce.customer.service.InvitationDetailsService;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvitationDetailsServiceImpl implements InvitationDetailsService {

	@Autowired
	private InvitationDetailsDao invitationDetailsDao;

	@Autowired
	private AccountDetailsDao accountDetailsdao;

	@Override
	@Transactional
	public String createInvitation(BusinessAccount businessaccountreq) {

		if (!accountDetailsdao.validateAccountType(businessaccountreq.getBisAccountId(), "Business Account"))
			throw new BusinessValidationException("Invalid Business Account !!");

		businessaccountreq.getAccounts().stream().forEach(a -> {
			if (invitationDetailsDao.findSubAccountDetailsByAccountId(a.getAccountId()) != null)
				throw new BusinessValidationException(
						"One of the account is already linked to the other business account !!");
			InvitationDetails invdetails = new InvitationDetails();
			invdetails.setBaId(businessaccountreq.getBisAccountId());
			invdetails.setAccountId(a.getAccountId());
			invdetails.setStatus("NEW");
			invitationDetailsDao.createInvitation(invdetails);
		});
		return "Invite successfuly send !!";
	}

	@Override
	@Transactional
	public void processInvitation(UUID baId, UUID accountId, String action) {
		invitationDetailsDao.processInvitation(baId, accountId, action);

	}
}
