package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidCategoryIdException.class)
	public ResponseEntity<Object> handleException(InvalidCategoryIdException exception, WebRequest request){
		return handleExceptionInternal(exception,exception.toString(),new HttpHeaders() ,HttpStatus.BAD_REQUEST,request);
	}
	@ExceptionHandler(value = InvalidCategoryDataException.class)
	public ResponseEntity<Object> handleCategoryDtaError(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	@ExceptionHandler(value = InvalidAuthorizationTokenException.class)
	public ResponseEntity<Object> handleTokenError(RuntimeException exception, WebRequest request){
		String bodyOfResponse = exception.toString();
		return handleExceptionInternal(exception, bodyOfResponse,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	@ExceptionHandler(value = InvalidUserNameException.class)
	public ResponseEntity<Object> handleUsernameError(RuntimeException exception, WebRequest request){
		String bodyOfResponse = exception.toString();
		return handleExceptionInternal(exception, bodyOfResponse,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	

}
