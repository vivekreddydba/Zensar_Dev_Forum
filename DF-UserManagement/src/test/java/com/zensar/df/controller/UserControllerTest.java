package com.zensar.df.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserService;

public class UserControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean 
	UserService userService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testRegisterUser() throws Exception{
		
	}
	
}
