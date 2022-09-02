package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public UserDto registerUser(UserDto userdto) {
		lastUserId = lastUserId+1;
		userdto.setId(lastUserId);
		users.add(userdto);
		return userdto;
	}
	
	
	
	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;
	@Override
	public boolean logoutUser(String auth) {
		if("A1B2C3D4".equals(auth)) {
			return true;
		}
		return false;
	}

}
