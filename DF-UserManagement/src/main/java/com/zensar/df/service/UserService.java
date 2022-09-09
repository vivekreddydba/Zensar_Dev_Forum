package com.zensar.df.service;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zensar.df.dto.UserDto;

public interface UserService {
	UserDto registerUser(UserDto userdto);
	Boolean logoutUser(String auth);
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	List<UserDto> findUserByUsername(String username) throws UsernameNotFoundException;
	
}
