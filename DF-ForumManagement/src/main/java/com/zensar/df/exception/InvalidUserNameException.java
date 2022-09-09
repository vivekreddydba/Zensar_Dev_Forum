package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUserNameException extends RuntimeException{
	private String msg;
	public InvalidUserNameException() {
		this.msg="";
	}
	public InvalidUserNameException(String msg) {
		this.msg=msg;
	} 
	public String toString() {
		return "UserName already exist: " + this.msg;
	}
	
}
