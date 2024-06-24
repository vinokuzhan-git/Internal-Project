package com.suphse.ecommerce.customer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.suphse.ecommerce.customer.dao.OrderDetailsDao;
import com.suphse.ecommerce.customer.entity.OrderDetails;
import com.suphse.ecommerce.customer.repository.OrderDetailsRepository;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	@Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDetails> findOrdersForAccountAndSubAccounts(UUID accountId) {
        return orderDetailsRepository.findOrdersForAccountAndSubAccounts(accountId);
    }
    
    @Override
    public OrderDetails createOrder(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}

