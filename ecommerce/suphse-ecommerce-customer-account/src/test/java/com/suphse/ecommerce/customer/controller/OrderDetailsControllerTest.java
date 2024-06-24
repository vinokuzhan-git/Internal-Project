package com.suphse.ecommerce.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suphse.ecommerce.customer.controller.OrderDetailsController;
import com.suphse.ecommerce.customer.entity.OrderDetails;
import com.suphse.ecommerce.customer.service.OrderDetailsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderDetailsController.class)
@AutoConfigureMockMvc
public class OrderDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDetailsService orderDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetOrdersForAccountAndSubAccounts() throws Exception {
        UUID accountId = UUID.randomUUID();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(UUID.randomUUID());
        orderDetails.setAccountId(accountId);
        orderDetails.setProductName("Product A");
        orderDetails.setPrice(100.0);
        List<OrderDetails> orders = Collections.singletonList(orderDetails);

        when(orderDetailsService.getOrdersForAccountAndSubAccounts(accountId)).thenReturn(orders);

        mockMvc.perform(get("/order/details/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setAccountId(UUID.randomUUID());
        orderDetails.setProductName("Product A");
        orderDetails.setPrice(100.0);

        when(orderDetailsService.createOrder(any(OrderDetails.class))).thenReturn(orderDetails);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDetails)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(orderDetails)));
    }
}

