package com.suphse.ecommerce.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suphse.ecommerce.customer.entity.AccountDetails;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails,UUID> {

	Optional<AccountDetails> findById(UUID accountId);
	
	AccountDetails findByEmail(String email);
	
	AccountDetails findByEmailAndId(String email,UUID id);
	
	boolean existsByIdAndAccountType(UUID id,String accountType);

}
