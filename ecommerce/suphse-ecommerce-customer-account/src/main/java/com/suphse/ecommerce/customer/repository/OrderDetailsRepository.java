package com.suphse.ecommerce.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suphse.ecommerce.customer.entity.OrderDetails;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID> {

    @Query(value = "SELECT * FROM order_details WHERE account_id = :accountId " +
                   "UNION " +
                   "SELECT * FROM order_details WHERE account_id IN " +
                   "(SELECT account_id FROM sub_account_details WHERE ba_id = :accountId)", nativeQuery = true)
    public List<OrderDetails> findOrdersForAccountAndSubAccounts(@Param("accountId") UUID accountId);
    
    @Query("SELECT COUNT(1) FROM OrderDetails o WHERE o.accountId = :accountId")
    public int countOrdersByAccountId(UUID accountId);
}
