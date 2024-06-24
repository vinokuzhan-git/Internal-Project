package com.suphse.ecommerce.customer.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suphse.ecommerce.customer.config.Action;
import com.suphse.ecommerce.customer.dao.InvitationDetailsDao;
import com.suphse.ecommerce.customer.entity.InvitationDetails;
import com.suphse.ecommerce.customer.entity.SubAccountDetails;
import com.suphse.ecommerce.customer.exception.BusinessValidationException;
import com.suphse.ecommerce.customer.exception.CustomNotFoundException;
import com.suphse.ecommerce.customer.repository.InvitationDetailsRepository;
import com.suphse.ecommerce.customer.repository.SubAccountDetailsRepository;

import jakarta.transaction.Transactional;

@Component
public class InvitationDetailsDaoImpl implements InvitationDetailsDao {

	@Autowired
	private InvitationDetailsRepository invitationDetailsRepository;

	@Autowired
	private SubAccountDetailsRepository subAccountDetailsRepository;

	@Override
	public InvitationDetails createInvitation(InvitationDetails invitationDetails) {
		return invitationDetailsRepository.save(invitationDetails);
	}

	@Override
	public boolean existsByBaIdAndAccountId(UUID baId, UUID accountId) {
		return invitationDetailsRepository.existsByBaIdAndAccountId(baId, accountId);
	}

	@Override
	public void processInvitation(UUID baId, UUID accountId, String action) {
		InvitationDetails invitationDetails = invitationDetailsRepository.findByBaIdAndAccountId(baId, accountId);

		if (invitationDetails == null) {
			SubAccountDetails subactdetails = subAccountDetailsRepository.findByBaIdAndAccountId(baId, accountId);
			if (subactdetails == null)
				throw new CustomNotFoundException(
						"Invitation not found for baId: " + baId + " and accountId: " + accountId);
		}

		if (Action.ACCEPT.getValue().equalsIgnoreCase(action)) {
			// Create record in sub_account_details table
			SubAccountDetails subAccountDetails = new SubAccountDetails();
			subAccountDetails.setBaId(baId);
			subAccountDetails.setAccountId(accountId);
			// Set creation and update details if required
			subAccountDetailsRepository.save(subAccountDetails);

			// Remove record from InvitationDetails table
			invitationDetailsRepository.delete(invitationDetails);
		} else if (Action.REJECT.getValue().equalsIgnoreCase(action)) {
			// Remove record from InvitationDetails table
			invitationDetailsRepository.delete(invitationDetails);
		} else if (Action.UNLINK.getValue().equalsIgnoreCase(action)) {
			// Remove record from sub_account_details table if it exists
			SubAccountDetails existingSubAccount = subAccountDetailsRepository.findByBaIdAndAccountId(baId, accountId);
			if (existingSubAccount != null) {
				subAccountDetailsRepository.delete(existingSubAccount);
			}
		} else {
			throw new BusinessValidationException("Invalid action provided: " + action);
		}
	}
	
	@Override
	public SubAccountDetails findSubAccountDetailsByAccountId(UUID accountId)
	{
		return subAccountDetailsRepository.findByAccountId(accountId);
	}
	
}
