package com.zensar.df.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.df.model.User;
//import org.springframework.data.repository.CrudRepository;



public interface UserRepo extends JpaRepository<User,String> {

	
	
}

