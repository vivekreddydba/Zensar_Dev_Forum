package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidqusIdException extends RuntimeException {
	private String msg;
	public InvalidqusIdException() {
		this.msg="";
	}
	public InvalidqusIdException(String msg) {
		this.msg=msg;
	}
	public String toString() {
		return "Invalid question Id:" +this.msg;
	}

}
