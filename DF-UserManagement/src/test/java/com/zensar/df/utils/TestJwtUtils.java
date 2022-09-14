package com.zensar.df.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.entity.UserEntity;
import com.zensar.df.repo.UserRepo;
import com.zensar.df.service.UserServiceImpl;

//@WebMvcTest(JwtUtils.class)
public class TestJwtUtils {
	@Autowired
	MockMvc mockMvc;
	
	UserDetailsService userDetailsService;
	
	JwtUtils jwtUtils;
	
	@BeforeEach
	public void beforeTestCaseRun() {
		jwtUtils = new JwtUtils();
    }
	
	UserServiceImpl userService;
	
	@BeforeEach
	public void beforeTestCaseRuns() {
		userService = new UserServiceImpl();
    }
	
	@Test
	public void testValidateToken() throws Exception{
		
		UserRepo userRepo = mock(UserRepo.class);
		jwtUtils = mock(JwtUtils.class);
		userDetailsService=mock(UserDetailsService.class);
		userService.setUserRepo(userRepo);
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "ebc12dc");
        String jwtToken="ebc12dc";
        List<UserEntity> userList=new ArrayList<>();
		UserEntity userEntity=new UserEntity("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9988776655","ROLE_USER");
		userList.add(userEntity);
		when(userRepo.findByUsername(userEntity.getUsername())).thenReturn(userList);
		UserEntity userEntit=userList.get(0);
		List<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user=new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
        when(this.jwtUtils.extractUsername(jwtToken)).thenReturn("bindu");
        when(this.userDetailsService.loadUserByUsername(any())).thenReturn(null);
        when(this.jwtUtils.isTokenExpired(any())).thenReturn(false);
        when(jwtUtils.validateToken(any(), any())).thenCallRealMethod();
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        Boolean res=jwtUtils.validateToken(jwtToken, userDetails);
        assertEquals(true,res);
	}

}
