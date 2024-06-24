package com.suphse.ecommerce.customer.error;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	
	private String errorCode;
	
	private String errorMessage;
	
	private long timeStamp;
	
	private String path;
	
	private Map<Object, Object> errors = new HashMap<>();

	public ErrorResponse(String errorCode, String errorMessage, String path) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.path = path;
		this.timeStamp = System.currentTimeMillis();
	}
	
	

	public ErrorResponse(String errorCode, Map<Object, Object> errors, String path) {
		super();
		this.errorCode = errorCode;
		this.timeStamp = System.currentTimeMillis();
		this.path = path;
		this.errors = errors;
	}



	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getPath() {
		return path;
	}



	public Map<Object, Object> getErrors() {
		return errors;
	}



	public void setErrors(Map<Object, Object> errors) {
		this.errors = errors;
	}



	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", timeStamp=" + timeStamp
				+ ", path=" + path + ", errors=" + errors + "]";
	}

	

}
