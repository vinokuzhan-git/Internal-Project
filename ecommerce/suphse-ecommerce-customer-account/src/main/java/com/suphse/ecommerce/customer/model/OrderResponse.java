package com.suphse.ecommerce.customer.model;

public class OrderResponse {

    private String orderId;

    // Constructor
    public OrderResponse(String orderId) {
        this.orderId = orderId;
    }

    // Getter and Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
