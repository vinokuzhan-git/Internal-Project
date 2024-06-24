package com.suphse.ecommerce.customer.dao;

import java.util.UUID;

import com.suphse.ecommerce.customer.entity.InvitationDetails;
import com.suphse.ecommerce.customer.entity.SubAccountDetails;

public interface InvitationDetailsDao {
	
	public InvitationDetails createInvitation(InvitationDetails invitationDetails);
	
	public boolean existsByBaIdAndAccountId(UUID baId, UUID accountId);
    public void processInvitation(UUID baId, UUID accountId, String action);

	public SubAccountDetails findSubAccountDetailsByAccountId(UUID accountId);

}
