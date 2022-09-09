package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRoleException extends RuntimeException{
	private String msg;
	public InvalidRoleException() {
		this.msg="";
	}
	public InvalidRoleException(String msg) {
		this.msg=msg;
	} 
	public String toString() {
		return "Only admin can update: " + this.msg;
	}
	
}
