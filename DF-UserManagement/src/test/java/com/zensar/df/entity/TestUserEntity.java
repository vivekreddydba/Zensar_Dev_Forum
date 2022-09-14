package com.zensar.df.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(UserEntity.class)
public class TestUserEntity {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserDetailsService userDetailsService;
	
	@Test
	public void testUserEntityByLastname() throws Exception{
		UserEntity user=new UserEntity();
		user.setFirstname("Anand");
		user.setLastname("Kulkarni");
		user.setUsername("anand");
		user.setPassword("anand123");
		user.setEmail("anand@gmail.com");
		user.setPhone("9999999999");
		user.setRole("ROLE_ADMIN");
		assertEquals("Kulkarni", user.getLastname());
	}
	
	@Test
	public void testUserEntityByFirstname() throws Exception{
		UserEntity user=new UserEntity();
		user.setFirstname("Bindu");
		user.setLastname("Madhavi");
		user.setUsername("bindu");
		user.setPassword("bindu123");
		user.setEmail("bindu@gmail.com");
		user.setRole("ROLE_USER");
		assertEquals("Bindu", user.getFirstname());
	}
	
	@Test
	public void testUserEntityByUsername() throws Exception{
		UserEntity user=new UserEntity();
		user.setFirstname("Bindu");
		user.setLastname("Madhavi");
		user.setUsername("bindu");
		user.setPassword("bindu123");
		user.setEmail("bindu@gmail.com");
		user.setPhone("9999999999");
		user.setRole("ROLE_USER");
		assertEquals("bindu", user.getUsername());
	}

	@Test
	public void testGetParameterisedArguments() throws Exception{
		UserEntity user =  new UserEntity("anand","kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_ADMIN");
		assertEquals("anand",user.getFirstname());
		assertEquals("kulkarni",user.getLastname());
		assertEquals("anand",user.getUsername());
		assertEquals("9999999999",user.getPhone());
		assertEquals("anand123",user.getPassword());
		assertEquals("ROLE_ADMIN",user.getRole());
		
	}
	
	@Test
	public void testGetParameteriseArguments() throws Exception{
		UserEntity user =  new UserEntity(1,"anand","kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_ADMIN");
		assertEquals(1,user.getId());
		assertEquals("anand",user.getFirstname());
		assertEquals("kulkarni",user.getLastname());
		assertEquals("anand",user.getUsername());
		assertEquals("9999999999",user.getPhone());
		assertEquals("anand123",user.getPassword());
		assertEquals("ROLE_ADMIN",user.getRole());
		
	}
	

}
