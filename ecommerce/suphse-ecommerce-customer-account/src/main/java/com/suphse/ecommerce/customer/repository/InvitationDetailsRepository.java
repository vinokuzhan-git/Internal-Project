package com.suphse.ecommerce.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suphse.ecommerce.customer.entity.InvitationDetails;

import java.util.UUID;

@Repository
public interface InvitationDetailsRepository extends JpaRepository<InvitationDetails, UUID> {

	public InvitationDetails findByBaIdAndAccountId(UUID baId, UUID accountId);
	public boolean existsByBaIdAndAccountId(UUID baId, UUID accountId);
}
