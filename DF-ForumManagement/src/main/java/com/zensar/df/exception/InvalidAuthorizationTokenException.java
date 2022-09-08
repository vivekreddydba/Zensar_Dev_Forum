package com.zensar.df.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAuthorizationTokenException extends RuntimeException {
	private String msg;
	public InvalidAuthorizationTokenException() {
		this.msg="";
	}
	public InvalidAuthorizationTokenException(String msg) {
		this.msg=msg;
	} 
	public String toString() {
		return "Invalid Authorization Token: " + this.msg;
	}
	
}
