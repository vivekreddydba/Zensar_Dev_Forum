package com.zensar.df.controller;

import static org.hamcrest.CoreMatchers.containsString;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserService;
import com.zensar.df.utils.JwtUtils;


@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@MockBean
	UserDetailsService userDetailsService;
	
	
	@MockBean
	JwtUtils jwtUtils;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Test
	public void testRegisterUser() throws Exception{
		UserDto user = new UserDto();
		user.setFirstname("Anand");
		user.setLastname("Kulkarni");
		user.setUsername("anand");
		user.setPassword("anand123");
		user.setEmail("anand@gmail.com");
		user.setPhone("9999999999L");
		when(this.userService.registerUser(user)).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:8000/devforum/user/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user))
				).andExpect(status().isOk())
				.andExpect(content().string(containsString("Anand")))
				.andReturn();
	            String response = mvcResult.getResponse().getContentAsString();
	            assertEquals(response.contains("Anand"), true);
	}
	
	@Test
	public void testRegisterUserBlankFirstName() throws Exception{
		UserDto user = new UserDto();
		user.setFirstname("");
		user.setLastname("Kulkarni");
		user.setUsername("anand");
		user.setPassword("anand123");
		user.setEmail("anand@gmail.com");
		user.setPhone("9999999999L");
		when(this.userService.registerUser(user)).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:8000/devforum/user/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user))
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	public void testlogoutInvaliduser() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3D4");
		when(this.jwtUtils.extractUsername(any())).thenReturn(null);
		when(this.jwtUtils.validateToken(any(), any())).thenReturn(false);
		when(this.userDetailsService.loadUserByUsername(any())).thenReturn(null);
		when(this.userService.logoutUser("A1B2C16S")).thenReturn(false);
		MvcResult mvcResult = this.mockMvc.perform(delete("http://localhost:8000/devforum/user/logout")
				.headers(httpHeaders))
				.andExpect(status().isBadRequest())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.equals("false"),true);
	}
	@Test
	public void testlogoutuser() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C16S");
		when(this.jwtUtils.extractUsername(any())).thenReturn(null);
		when(this.jwtUtils.validateToken(any(), any())).thenReturn(true);
		when(this.userDetailsService.loadUserByUsername(any())).thenReturn(null);
		when(this.userService.logoutUser("A1B2C16S")).thenReturn(true);
		MvcResult mvcResult = this.mockMvc.perform(delete("http://localhost:8000/devforum/user/logout")
				.headers(httpHeaders))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.equals("true"),true);
	}
    
@Test
public void test2getUserInfo() throws Exception {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", "A54BG"); 
    List<UserDto> user =new ArrayList<>();
    user.add(new UserDto(1,null, " lastname"," String username", "String password", "String email", "1234455588", "String role"));
    when(this.jwtUtils.extractUsername("A54")).thenReturn(null);
    when(this.userService.findUserByUsername(any())).thenReturn(user);
    when(this.jwtUtils.validateToken(any(),any())).thenReturn(false); 
    MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8000/devforum/user")
            .headers(httpHeaders))
            .andExpect(status().isBadRequest())
            .andReturn();
           
    String response = mvcResult.getResponse().getContentAsString();
    assertEquals(response.contains("null"), true);
    }
	 @Test
     public void testgetUserInfo() throws Exception {
         HttpHeaders httpHeaders = new HttpHeaders();
         httpHeaders.set("Authorization", "A54BG");
         List<UserDto> user =new ArrayList<>();
         user.add(new UserDto(1,"firstname", " lastname"," String username", "String password", "String email", "1234455577", "String role"));
         when(this.jwtUtils.extractUsername("A54BG")).thenReturn("string");
         when(this.userService.findUserByUsername(any())).thenReturn(user);
         when(this.jwtUtils.validateToken(any(),any())).thenReturn(true);
         
         MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8000/devforum/user")
                 .headers(httpHeaders))
                 .andExpect(status().isAccepted())
                 .andReturn();
         String response = mvcResult.getResponse().getContentAsString();
         assertEquals(response.contains("lastname"), true);
	 }
                 
 
	
}
