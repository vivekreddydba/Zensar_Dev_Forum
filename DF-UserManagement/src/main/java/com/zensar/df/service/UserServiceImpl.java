package com.zensar.df.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import org.apache.catalina.mapper.Mapper;
import org.apache.catalina.mapper.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.zensar.df.dto.UserDto;
import com.zensar.df.entity.BlackListEntity;
import com.zensar.df.entity.UserEntity;
import com.zensar.df.exception.InvalidUserNameException;

import org.springframework.web.bind.annotation.RequestHeader;
import com.zensar.df.repo.BlacklistRepo;
import com.zensar.df.repo.UserRepo;
import com.zensar.df.utils.JwtUtils;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	BlacklistRepo blackListRepo;
	


	@Autowired
	ModelMapper mapper;

	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;
	
	@Autowired
	BlacklistRepo Blrepo;
	
	@Override
    public Boolean logoutUser(String auth) {
    	BlackListEntity be=new BlackListEntity(auth, LocalDate.now());
    	be=Blrepo.save(be);
    	return true;
    }
	@Override
	public UserDto registerUser(UserDto userdto) {
		UserEntity userEntity=new UserEntity(userdto.getFirstname(), userdto.getLastname(), userdto.getUsername(), userdto.getPassword(), userdto.getEmail(), userdto.getPhone(), userdto.getRole());
		List<UserEntity> userEntityList =this.userRepo.findByUsername(userdto.getUsername());
		if(userEntityList==null || userEntityList.size()==0) {
			  userEntity=userRepo.save(userEntity);
			  userdto=new UserDto(userEntity.getId(), userEntity.getFirstname(), userEntity.getLastname(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getPhone(), userEntity.getRole());
			  return userdto;
		}
		throw new InvalidUserNameException(userdto.getUsername());

	}
	
	@Override
	public String getRole(String username) throws UsernameNotFoundException {
	List<UserEntity> userEntityList =userRepo.findByUsername(username);
	if(userEntityList==null || userEntityList.size()==0) {
		throw new UsernameNotFoundException(username);
	}
	
	UserEntity userEntity = userEntityList.get(0);
	return userEntity.getRole();
	
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity>userEntityList =userRepo.findByUsername(username);
		if(userEntityList==null || userEntityList.size()==0) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user = new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
		return user;
		
	}
	
	@Override
	public List<UserDto> findUserByUsername(String username) throws UsernameNotFoundException {
		// Write user Entity and UserRepo
		List<UserEntity>userEntityList = userRepo.findByUsername(username);
		return userEntityList.stream().map(i -> mapper.map(i, UserDto.class)).collect(Collectors.toList());
		
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public void setBlackListRepo(BlacklistRepo blackListRepo) {
		this.Blrepo = blackListRepo;
	}
	
}
