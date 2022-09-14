package com.zensar.df.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zensar.df.repo.UserRepo;

public class UserServiceTest {

	
	@MockBean
	UserRepo userRepo;
	
	@InjectMocks
	UserService userService;
	
	
	@Test
	public void testLoginwhenCreatingNewUser(){
		String username="divya";
		
//		Mockito.when(userRepo.loadUserByUsername(username)).thenReturn
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
