package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCategoryDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidCategoryDataException(String message) {
		this.message=message;
	}
	@Override
	public String toString() {
		return "Invalid Category Date:" + message;
	}

}
