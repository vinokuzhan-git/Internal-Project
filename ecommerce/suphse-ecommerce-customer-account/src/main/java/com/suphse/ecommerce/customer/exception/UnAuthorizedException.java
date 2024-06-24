package com.suphse.ecommerce.customer.exception;

public class UnAuthorizedException extends RuntimeException {
	
	public UnAuthorizedException(String errorMessage)
	{
		super(errorMessage);
	}

}
