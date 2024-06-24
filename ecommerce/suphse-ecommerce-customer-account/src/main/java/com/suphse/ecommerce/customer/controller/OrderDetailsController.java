package com.suphse.ecommerce.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suphse.ecommerce.customer.entity.OrderDetails;
import com.suphse.ecommerce.customer.service.OrderDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Tag(name=  "Order Details")
@RestController
@RequestMapping("/order")
public class OrderDetailsController {

	@Autowired
    private OrderDetailsService orderDetailsService;

	@Operation(method = "Order History", summary = "Fetch Order details using account id")
    @GetMapping("/details/{accountId}")
    public ResponseEntity<List<OrderDetails>> getOrdersForAccountAndSubAccounts(@PathVariable UUID accountId) {
        List<OrderDetails> orders = orderDetailsService.getOrdersForAccountAndSubAccounts(accountId);
        return ResponseEntity.ok(orders);
    }
    
	@Operation(method = "Create Order", summary = "Create Order API")
    @PostMapping
    public ResponseEntity<OrderDetails> createOrder(@Valid @RequestBody OrderDetails orderDetails) {
        OrderDetails createdOrder = orderDetailsService.createOrder(orderDetails);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}

