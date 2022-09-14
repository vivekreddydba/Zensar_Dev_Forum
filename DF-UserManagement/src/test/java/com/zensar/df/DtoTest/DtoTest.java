package com.zensar.df.DtoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.zensar.df.dto.UserDto;

@WebMvcTest(UserDto.class)
public class DtoTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserDetailsService userDetailsService;
	
	@Test
	public void testGetUserDTOReturnsCorrectJson() throws Exception {
		UserDto user = new UserDto();
		user.setFirstname("anand");
		user.setLastname("Kulkarni");
		user.setUsername("anand");
		user.setPassword("anand123");
		user.setEmail("anand@gmail.com");
		user.setPhone("9999999999");
		user.setRole("ROLE_ADMIN");
		assertEquals(user.getFirstname(),"anand");
	}
	
	@Test
	public void testGetParameterisedArguments() throws Exception{
		UserDto user =  new UserDto("anand","kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_ADMIN");
		assertEquals(user.getFirstname(),"anand");
		assertEquals(user.getLastname(),"kulkarni");
		assertEquals(user.getUsername(),"anand");
		//assertEquals(user.getPhone(),"9999999999");
		assertEquals(user.getPassword(),"anand123");
		//assertEquals(user.getPhone(),"ROLE_ADMIN");
		
	}
	
	@Test
	public void testGetParameteriseArguments() throws Exception{
		UserDto user =  new UserDto(1,"bindu","madhavi","bindu","bindu23","bindu@gmail.com","9999999999","ROLE_USER");
		assertEquals(user.getFirstname(),"bindu");
		assertEquals(user.getLastname(),"madhavi");
		assertEquals(user.getUsername(),"bindu");
		assertEquals(user.getPassword(),"bindu23");
		
	}
	
	@Test
	public void testUserDtoEquals() throws Exception{
		UserDto user1=new UserDto("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9999999999","ROLE_USER");
		UserDto user2=new UserDto("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9999999999","ROLE_USER");
		assertTrue(user1.equals(user2));
		
	}
	
	@Test
	public void testUserDtoEquals2() throws Exception{
		UserDto user1=new UserDto("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9999999999","ROLE_USER");
		UserDto user2=new UserDto("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_ADMIn");
		assertFalse(user1.equals(user2));
	}
	

}
