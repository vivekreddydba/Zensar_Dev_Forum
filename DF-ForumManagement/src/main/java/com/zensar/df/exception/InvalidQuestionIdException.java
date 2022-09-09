package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuestionIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;
	public InvalidQuestionIdException() {
		this.message=" ";
	}
	public InvalidQuestionIdException(String msg) {
		this.message="";
	}
	public String toString() {
		return "Invalid Question Id:" +message;
	}

}
