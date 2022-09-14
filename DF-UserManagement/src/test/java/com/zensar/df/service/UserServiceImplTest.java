package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.UserDto;
import com.zensar.df.entity.BlackListEntity;
import com.zensar.df.entity.UserEntity;
import com.zensar.df.repo.BlacklistRepo;
import com.zensar.df.repo.UserRepo;

public class UserServiceImplTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserDetailsService userDetailsService;
	
	@MockBean
	ModelMapper mapper;
	
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
        UserEntity userEntity = new UserEntity("anand", " kulkarni","anand", "anand123", "anand@123", "12344555", "ROLE_USER");
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        when(userRepo.save(any())).thenReturn(userEntity);
        when(userRepo.findByUsername("bhagya")).thenReturn(userList);

        UserDto userDto = this.userService.registerUser(user);
        

        assertEquals("anand", user.getUsername());
    }
	
	@Test
	public void testByFirstnameRegisterUser() throws Exception{
		UserRepo userRepo = mock(UserRepo.class);
		userService.setUserRepo(userRepo);
        UserDto user=new UserDto("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_USER");
        UserEntity userEntity = new UserEntity("anand", " kulkarni","anand", "anand123", "anand@123", "12344555", "ROLE_USER");
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        when(userRepo.save(any())).thenReturn(userEntity);
        when(userRepo.findByUsername("bhagya")).thenReturn(userList);

        UserDto userDto = this.userService.registerUser(user);
        

        assertEquals("Anand", user.getFirstname());
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
	
	@Test
	public void testGetRole() throws Exception{
		UserRepo userRepo=mock(UserRepo.class);
		userService.setUserRepo(userRepo);
		UserEntity userEntity=new UserEntity("Anand", "Kulkarni", "anand", "anand123", "anand@gmail.com", "9999999999", "ROLE_ADMIN");
		List<UserEntity> userList=new ArrayList<>();
		userList.add(userEntity);
		when(userRepo.findByUsername("anand")).thenReturn(userList);
		String role=this.userService.getRole(userEntity.getUsername());
		assertEquals("ROLE_ADMIN",role);
	}
	
	@Test
	public void testGetRoleUser() throws Exception{
		UserRepo userRepo=mock(UserRepo.class);
		userService.setUserRepo(userRepo);
		UserEntity userEntity=new UserEntity("Bindu","Madhavi","bindu","bindu123","bindu@gmal.com","9999999999","ROLE_USER");
		List<UserEntity> userList=new ArrayList<>();
		userList.add(userEntity);
		when(userRepo.findByUsername("bindu")).thenReturn(userList);
		String role=this.userService.getRole(userEntity.getUsername());
		assertEquals("ROLE_USER",role);
	}
	
	@Test
	public void testLoginUser() throws Exception{
		UserRepo userRepo=mock(UserRepo.class);
		userService.setUserRepo(userRepo);
		UserEntity userEntity=new UserEntity("Anand", "Kulkarni", "anand", "anand123", "anand@gmail.com", "9999999999", "ROLE_ADMIN");
		List<UserEntity> userList=new ArrayList<>();
		userList.add(userEntity);
		when(userRepo.findByUsername(userEntity.getUsername())).thenReturn(userList);
		UserEntity userEntit=userList.get(0);
		List<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user=new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
		assertEquals(userEntity.getUsername(),user.getUsername());
	}	
	
	@Test
	public void testReturnsInfo() throws Exception{
		UserRepo userRepo=mock(UserRepo.class);
		userService.setUserRepo(userRepo);
		List<UserDto> userList=new ArrayList<>();
		List<UserEntity> userEntity=new ArrayList<>();
		UserDto user=new UserDto("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_USER");
		UserEntity entityUser=new UserEntity("Anand","Kulkarni","anand","anand123","anand@gmail.com","9999999999","ROLE_USER");
		userList.add(user);
		userEntity.add(entityUser);
		when(userRepo.findByUsername(entityUser.getUsername())).thenReturn(userEntity);
		List<UserDto> userDtoList=this.userService.findUserByUsername(entityUser.getUsername());
		assertEquals(userDtoList.contains(user),true);
	}
	
	@Test
	public void testNewReturnsInfo() throws Exception{
		UserRepo userRepo=mock(UserRepo.class);
		userService.setUserRepo(userRepo);
		List<UserDto> userList=new ArrayList<>();
		List<UserEntity> userEntity=new ArrayList<>();
		UserDto user=new UserDto("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9988776655","ROLE_USER");
		UserEntity entityUser=new UserEntity("Bindu","Madhavi","bindu","bindu123","bindu@gmail.com","9988776655","ROLE_USER");
		userList.add(user);
		userEntity.add(entityUser);
		when(userRepo.findByUsername(entityUser.getUsername())).thenReturn(userEntity);
		List<UserDto> userDtoList=this.userService.findUserByUsername(entityUser.getUsername());
		assertEquals(userDtoList.contains(user),true);
	}
}
