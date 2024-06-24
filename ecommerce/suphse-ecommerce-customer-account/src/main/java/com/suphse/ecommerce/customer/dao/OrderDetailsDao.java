package com.suphse.ecommerce.customer.dao;

import java.util.List;
import java.util.UUID;

import com.suphse.ecommerce.customer.entity.OrderDetails;

public interface OrderDetailsDao {
	public OrderDetails createOrder(OrderDetails orderDetails);
    public List<OrderDetails> findOrdersForAccountAndSubAccounts(UUID accountId);
}

