package com.suphse.ecommerce.customer.controller.advice;

import java.net.http.HttpHeaders;
import java.util.HashMap;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suphse.ecommerce.customer.error.ErrorResponse;
import com.suphse.ecommerce.customer.exception.BusinessValidationException;
import com.suphse.ecommerce.customer.exception.CustomNotFoundException;
import com.suphse.ecommerce.customer.exception.InternalServerException;
import com.suphse.ecommerce.customer.exception.UnAuthorizedException;

import jakarta.validation.ConstraintViolationException;

@Order(1)
@ControllerAdvice
public class EcommerceControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessValidationException.class)
    public final ResponseEntity<ErrorResponse> handleBusinessValidationException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("VALIDATION_EXCEPTION",ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InternalServerException.class)
	public final ResponseEntity<ErrorResponse> handleInternalServerException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_EXCEPTION",ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(CustomNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND_EXCEPTION",ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UnAuthorizedException.class)
	public final ResponseEntity<ErrorResponse> handleUnAuthorizedException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("UN_AUTHORIZE_EXCEPTION",ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
	
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	/* protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request)  {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("FIELD_EXCEPTION",errors,request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
*/
}
