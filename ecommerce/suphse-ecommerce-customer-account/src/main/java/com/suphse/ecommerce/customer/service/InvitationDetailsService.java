package com.suphse.ecommerce.customer.service;

import java.util.UUID;

import com.suphse.ecommerce.customer.entity.InvitationDetails;
import com.suphse.ecommerce.customer.model.BusinessAccount;

public interface InvitationDetailsService {
    public String createInvitation(BusinessAccount businessaccountreq);
    public void processInvitation(UUID baId, UUID accountId, String action);
}
