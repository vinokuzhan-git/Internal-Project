package com.suphse.ecommerce.customer.service;

import java.util.List;
import java.util.UUID;

import com.suphse.ecommerce.customer.entity.OrderDetails;

public interface OrderDetailsService {
	public OrderDetails createOrder(OrderDetails orderDetails);
    public List<OrderDetails> getOrdersForAccountAndSubAccounts(UUID accountId);
}

