package com.suphse.ecommerce.customer.exception;

public class InternalServerException extends RuntimeException {

	public InternalServerException(String errorMessage) {
		super(errorMessage);
	}

}
