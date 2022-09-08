package com.zensar.df.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceDelegateImpl implements UserServiceDelegate{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public boolean isLoggedInUser(String authToken) {
		HttpHeaders header=new HttpHeaders();
		header.set("Authorization", authToken);
		HttpEntity entity=new HttpEntity(header);
		ResponseEntity<Boolean> result =
	            this.restTemplate.exchange("http://localhost:8000/devforum/token/validate",  
	                    HttpMethod.GET, entity, Boolean.class);
	        return result.getBody();
				
	}

}
