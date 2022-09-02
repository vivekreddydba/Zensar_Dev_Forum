package com.zensar.df.service;


import com.zensar.df.dto.UserDto;

public interface UserService {
	UserDto registerUser(UserDto userdto);
	boolean logoutUser(String auth);

}
