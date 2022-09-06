package com.zensar.df.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.UserDto;
import com.zensar.df.entity.BlackListEntity;
import com.zensar.df.entity.UserEntity;
import com.zensar.df.repo.BlacklistRepo;
import com.zensar.df.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDto registerUser(UserDto userdto) {
		UserEntity userEntity=new UserEntity(userdto.getFirstname(), userdto.getLastname(), userdto.getUsername(), userdto.getPassword(), userdto.getEmail(), userdto.getPhone());
		userEntity=userRepo.save(userEntity);
		userdto=new UserDto(userEntity.getId(), userEntity.getFirst_name(), userEntity.getLast_name(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getPhone());
		return userdto;
	}
	static List<UserDto> users = new ArrayList<>();
	static int lastUserId = 0;
	
	@Autowired
	BlacklistRepo Blrepo;
	@Override
	public boolean logoutUser(String auth) {
		if("A1B2C3D7".equals(auth)) {
			BlackListEntity dbentity = new BlackListEntity(auth,LocalDate.now());
			dbentity=Blrepo.save(dbentity);
			return true;
		}
		return false;
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// Write user Entity and UserRepo
		List<UserEntity> userEntityList =userRepo.findByUsername(username);
		if(userEntityList==null || userEntityList.size()==0) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		User user = new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
		return user;
		
	}

}
