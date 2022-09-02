package com.zensar.df.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/devforum")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/user/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@RequestBody User login){
		login.setName("Bharath");
		return new ResponseEntity("348",HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/user", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto) {
		return new ResponseEntity(userService.registerUser(userdto),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping(value="/user/logout",produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean logoutUser(@RequestHeader("authorization") String authToken){
		if(userService.logoutUser(authToken)) {
			return true;
		}
		return false;
		
	}
	

}
