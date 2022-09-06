package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;
	public InvalidCategoryIdException() {
		this.message=" ";
	}
	public InvalidCategoryIdException(String msg) {
		this.message="";
	}
	public String toString() {
		return "Invalid Category Id:" +message;
	}

}
