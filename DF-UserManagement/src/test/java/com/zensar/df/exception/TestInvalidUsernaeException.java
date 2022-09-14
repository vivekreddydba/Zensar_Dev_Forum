package com.zensar.df.exception;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.UserDto;
import com.zensar.df.entity.UserEntity;
import com.zensar.df.repo.UserRepo;
import com.zensar.df.service.UserServiceImpl;



@WebMvcTest(InvalidAuthorizationTokenException.class)
public class TestInvalidUsernaeException {
	@Autowired
	MockMvc mockMVC;
	
	@MockBean
	UserDetailsService userdeailsService;
	
    UserServiceImpl userService;
	
	@BeforeEach
	public void beforeTestCaseRuns() {
		userService = new UserServiceImpl();
	}
	
	@Test
	public void testByFirstnameRegisterUser() throws Exception{
		UserRepo userRepo = mock(UserRepo.class);
		userService.setUserRepo(userRepo);
        UserDto user=new UserDto("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_USER");
        UserEntity userEntity = new UserEntity("Anand", " kulkarni","anand", "anand123", "anand@123", "12344555", "ROLE_USER");
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        when(userRepo.save(any())).thenReturn(userEntity);
        when(userRepo.findByUsername("bindu")).thenThrow(InvalidUserNameException.class);
        userService.registerUser(user);
        assertEquals(user.getUsername(),"anand");
	}
	
	

}
