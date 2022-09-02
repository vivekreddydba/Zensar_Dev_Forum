package com.zensar.df.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.df.dto.UserDto;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/devforum")
public class UserController {
	
	@PostMapping(value = "/user/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@RequestBody User login){
		login.setName("Bharath");
		return new ResponseEntity("348",HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/user", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public UserDto registerUser(@RequestBody UserDto userdto) {
		lastUserId = lastUserId+1;
		userdto.setId(lastUserId);
		users.add(userdto);
		return userdto;
		
		
	}
	
	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;

}
