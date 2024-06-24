package com.suphse.ecommerce.customer.exception;

public class CustomNotFoundException extends RuntimeException {
	
	public CustomNotFoundException(String errorMessage)
	{
		super(errorMessage);
	}

}
