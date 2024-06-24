package com.suphse.ecommerce.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suphse.ecommerce.customer.entity.SubAccountDetails;

import java.util.UUID;

@Repository
public interface SubAccountDetailsRepository extends JpaRepository<SubAccountDetails, UUID> {
	public SubAccountDetails findByBaIdAndAccountId(UUID baId, UUID accountId);
	
	public SubAccountDetails findByAccountId(UUID accountId);
}

