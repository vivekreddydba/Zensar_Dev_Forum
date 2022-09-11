package com.zensar.df.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@PostMapping(value="/user/authenticate", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Login a user/admin", notes="This request generates a unique jwt token for logging in")
	public ResponseEntity<String> authenticate(@RequestBody UserDto authRequest){
		System.out.println(authRequest);
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
					);
		}
		
		catch(BadCredentialsException e) {
			//throw new BadCredentialsException(authRequest.getUsername());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		String jwtToken = jwtUtils.generateToken(authRequest.getUsername());
		return new ResponseEntity<>(jwtToken,HttpStatus.OK);	
				
				
	}
	@GetMapping(value="/token/validate", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> isTokenValid(@RequestHeader("Authorization") String jwtToken) {
		jwtToken = jwtToken.substring(7, jwtToken.length());
		boolean isTokenValid = false;
		try {
			String username = jwtUtils.extractUsername(jwtToken);
			UserDetails userDetails = userService.loadUserByUsername(username);
			isTokenValid = jwtUtils.validateToken(jwtToken, userDetails);
		}
		catch(Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		if(isTokenValid) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;
	
	
	@PostMapping(value = "/user", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Registration of a user", notes="This request saves the details of user in the database")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userdto) {
		UserDto user=userService.registerUser(userdto);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
		
	}
	@DeleteMapping(value="/user/logout")
	@ApiOperation(value="Logout of a user", notes="This request moves the jwt token into blacklist and logs out the user")
	public ResponseEntity<Boolean> logoutUser(@RequestHeader("Authorization") String jwtToken) {
		boolean isTokenValid = this.isTokenValid(jwtToken).getBody();
		if(isTokenValid) {
			userService.logoutUser(jwtToken);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping(value="/user/role", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> getRole(@RequestHeader("Authorization") String jwtToken) {
		jwtToken = jwtToken.substring(7, jwtToken.length());
		boolean isTokenValid = false;
		String username = null;
		try {
			username = jwtUtils.extractUsername(jwtToken);
			UserDetails userDetails = userService.loadUserByUsername(username);
			isTokenValid = jwtUtils.validateToken(jwtToken, userDetails);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
		if(isTokenValid) {
			return new ResponseEntity<String>(userService.getRole(username), HttpStatus.OK);
		}
		return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}
	
	//returns user information
		@GetMapping(value="/user", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Returns user information", notes="This API endpoint is used for returning valid User Infor")
		public ResponseEntity<UserDto> returnUsersInfo (@RequestHeader("Authorization")String jwtToken){
			jwtToken = jwtToken.substring(7, jwtToken.length());
			boolean isTokenValid = false;
			UserDto user;
			String username=null;
			try {
				username = jwtUtils.extractUsername(jwtToken);
				UserDetails userDetails = userService.loadUserByUsername(username);
				isTokenValid = jwtUtils.validateToken(jwtToken, userDetails);
			}
			catch(Exception e) {
				return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
			}
			if(isTokenValid) {
				user = userService.findUserByUsername(username).get(0);		
				return new ResponseEntity<UserDto>(user,HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
			}
		}
					
}
