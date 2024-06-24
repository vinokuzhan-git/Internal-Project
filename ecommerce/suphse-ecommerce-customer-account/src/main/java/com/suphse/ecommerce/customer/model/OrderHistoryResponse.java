package com.suphse.ecommerce.customer.model;

import java.time.LocalDate;

public class OrderHistoryResponse {

    private String orderId;
    private String productName;
    private double totalAmount;
    private String accountId;
    private LocalDate orderDate;

    // Constructors
    public OrderHistoryResponse() {
    }

    public OrderHistoryResponse(String orderId, String productName, double totalAmount, String accountId, LocalDate orderDate) {
        this.orderId = orderId;
        this.productName = productName;
        this.totalAmount = totalAmount;
        this.accountId = accountId;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    @Override
    public String toString() {
        return "OrderHistoryResponse{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", totalAmount=" + totalAmount +
                ", accountId='" + accountId + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
