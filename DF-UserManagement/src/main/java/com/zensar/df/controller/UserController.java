package com.zensar.df.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserService;
import com.zensar.df.utils.JwtUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/devforum")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value="/user/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Login a user/admin", notes="This request generates a unique jwt token for logging in")
	public ResponseEntity<String> authenticate(@RequestBody UserDto authRequest){
		System.out.println(authRequest);
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
					);
		}
		
		catch(BadCredentialsException e) {
			throw new BadCredentialsException(authRequest.getUsername());
		}
		String jwtToken = jwtUtils.generateToken(authRequest.getUsername());
		return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
		
		
		
				
	
				
				
				
				
				
				
				
				
	}

@GetMapping("/all")
	public String viewAll() {
		return "hello All";
	}
	@GetMapping("/user")
	public String viewUser() {
		return "Hello User";
	}
	@GetMapping("/admin")
	public String viewAdmin() {
		return "Hello Admin";
	}
				
	
	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;
	
	@PostMapping(value = "/user", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Registration of a user", notes="This request saves the details of user in the database")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto) {
		UserDto user=userService.registerUser(userdto);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/user/logout",produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Logout of a user", notes="This request moves the jwt token into blacklist and logs out the user")
	public Boolean logoutUser(@RequestHeader("authorization") String authToken){
		if(userService.logoutUser(authToken)) {
			return true;
		}
		return false;
		
	}
	

}
