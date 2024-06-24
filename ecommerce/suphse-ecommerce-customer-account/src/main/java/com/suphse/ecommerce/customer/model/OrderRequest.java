package com.suphse.ecommerce.customer.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

public class OrderRequest {

    @NotNull(message = "accountId is mandatory")
    @Size(min = 1, message = "accountId must not be empty")
    private String accountId;

    @NotNull(message = "product is mandatory")
    @Size(min = 1, message = "product must not be empty")
    private String product;

    @NotNull(message = "totalPrice is mandatory")
    @Positive(message = "totalPrice must be positive")
    private Double totalPrice;

    // Getters and Setters

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
