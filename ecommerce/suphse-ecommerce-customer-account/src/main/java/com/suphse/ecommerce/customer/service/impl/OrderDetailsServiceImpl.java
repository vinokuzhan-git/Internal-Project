package com.suphse.ecommerce.customer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suphse.ecommerce.customer.dao.OrderDetailsDao;
import com.suphse.ecommerce.customer.entity.OrderDetails;
import com.suphse.ecommerce.customer.service.OrderDetailsService;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private OrderDetailsDao orderDetailsDao;

    @Override
    public List<OrderDetails> getOrdersForAccountAndSubAccounts(UUID accountId) {
        return orderDetailsDao.findOrdersForAccountAndSubAccounts(accountId);
    }
    
    @Override
    public OrderDetails createOrder(OrderDetails orderDetails) {
    	log.info(orderDetails.getPrice()+"");
    	System.out.println(orderDetails.getPrice());
        return orderDetailsDao.createOrder(orderDetails);
    }
}
