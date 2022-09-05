package com.zensar.df.controller;

import static org.hamcrest.CoreMatchers.containsString;
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
		UserDto userDto=new UserDto();
		userDto.setFirstname("Bindu");
		userDto.setLastname("Madhavi");
		userDto.setUsername("BinduPachala");
		userDto.setPassword("Bindu123");
		userDto.setEmail("bindhu.pachala@gmail.com");
		userDto.setPhone(846);
		System.out.print("User toString--" + userDto + "--");
		System.out.println("Test: hashCode--" + userDto.hashCode() + "--");
		when(this.userService.registerUser(userDto)).thenReturn(userDto);
		System.out.println("User string--" + objectMapper.writeValueAsString(userDto));
		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8000/devforum/user/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(userDto))
				).andExpect(content().string(containsString("Bindu")))
				.andReturn();
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Bindu"),true);
	}
	
	@Test
	public void testRegisterUser1() throws Exception{
		UserDto userDto=new UserDto();
		userDto.setFirstname("Bindu");
		userDto.setLastname("Madhavi");
		userDto.setUsername("BinduPachala");
		userDto.setPassword("Bindu123");
		userDto.setEmail("bindhu.pachala@gmail.com");
		userDto.setPhone(846);
		when(this.userService.registerUser(userDto)).thenReturn(userDto);
		when(this.userService.registerUser(null)).thenThrow(NullPointerException.class);
		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8000/devforum/user/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(userDto))
				).andExpect(content().string(containsString("Bindu")))
				.andReturn();
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains(response),true);
	}
	
	
}
