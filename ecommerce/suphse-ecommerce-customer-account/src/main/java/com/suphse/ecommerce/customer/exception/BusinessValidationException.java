package com.suphse.ecommerce.customer.exception;

public class BusinessValidationException extends RuntimeException {
	public BusinessValidationException(String errorMessage) {
		super(errorMessage);
	}
}
