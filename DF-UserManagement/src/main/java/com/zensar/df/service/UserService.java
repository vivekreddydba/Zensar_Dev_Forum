package com.zensar.df.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zensar.df.dto.UserDto;

public interface UserService {
	UserDto registerUser(UserDto userdto);
	boolean logoutUser(String auth);
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
