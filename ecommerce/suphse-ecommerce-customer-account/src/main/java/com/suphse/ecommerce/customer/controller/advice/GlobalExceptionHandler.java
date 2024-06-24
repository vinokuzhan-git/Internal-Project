package com.suphse.ecommerce.customer.controller.advice;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.suphse.ecommerce.customer.error.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        // Extract the validation errors from the exception
      /*  String errorMessage = ex.getConstraintViolations().stream()
                                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                                .findFirst()
                                .orElse("Validation error occurred"); */
    	Map<Object, Object> errorMap = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> cv.getMessage(),
                        (msg1, msg2) -> msg1 // If there are duplicate keys, choose the first message
                ));
    	
    	ErrorResponse errorResponse = new ErrorResponse("FIELD_EXCEPTION",errorMap,request.getDescription(false));

        // Create a custom error response
       // String responseBody = "Validation Error: " + errorMessage;
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

