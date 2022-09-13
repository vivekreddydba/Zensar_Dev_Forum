package com.zensar.df.service;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.UserDto;
import com.zensar.df.entity.BlackListEntity;
import com.zensar.df.entity.UserEntity;
import com.zensar.df.repo.BlacklistRepo;
import com.zensar.df.repo.UserRepo;

//@WebMvcTest(UserServiceImpl.class)
public class UserServiceImplTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserDetailsService userDetailsService;
	/*
	@MockBean
	UserRepo userRepo;
	
	@MockBean
	JwtUtils jwtUtils;

	@MockBean
	ModelMapper mapper;
	
	@MockBean
	BlacklistRepo blackListRepo;
	*/
	
	UserServiceImpl userService;
	
	@BeforeEach
	public void beforeTestCaseRuns() {
		userService = new UserServiceImpl();
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		UserRepo userRepo = mock(UserRepo.class);
		userService.setUserRepo(userRepo);
        UserDto user=new UserDto("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_USER");
        UserEntity userEntity = new UserEntity("anand", " kulkarni","anand", "anand123", "anand@123", "12344555", "ROLE_ADMIN");
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        when(userRepo.save(any())).thenReturn(userEntity);
        when(userRepo.findByUsername("bhagya")).thenReturn(userList);

        UserDto userDto = this.userService.registerUser(user);
        

        assertEquals("anand", user.getUsername());
    }
	
	@Test
	public void testLogoutUser() throws Exception{
		BlacklistRepo blackListRepo = mock(BlacklistRepo.class);
		userService.setBlackListRepo(blackListRepo);
		BlackListEntity be=new BlackListEntity();
		when(blackListRepo.save(be)).thenReturn(be);
		Boolean res=this.userService.logoutUser("A1B2C3");
		assertEquals(res,true);
		
	}
	
}
