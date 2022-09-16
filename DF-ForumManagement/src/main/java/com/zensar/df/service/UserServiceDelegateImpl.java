package com.zensar.df.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.zensar.df.repo.CategoryRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.exception.InvalidAuthorizationTokenException;

@Service
public class UserServiceDelegateImpl implements UserServiceDelegate{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CircuitBreaker(name="CB-USER-APP-SERVICE-CALL", fallbackMethod="fallbackForisLoggedInUser")
	public boolean isLoggedInUser(String authToken) {
		HttpHeaders header=new HttpHeaders();
		header.set("Authorization", authToken);
		HttpEntity entity=new HttpEntity(header);
		
		ResponseEntity<Boolean> result =
           this.restTemplate.exchange("http://API-GATEWAY/devforum/token/validate/",  
                HttpMethod.GET, entity, Boolean.class);
	    return result.getBody();
				
	}
	
	@Override
	@CircuitBreaker(name="CB-USER-APP-SERVICE-CALL", fallbackMethod="fallbackForisAdminRole")
public String isAdminRole(String authToken) {
		HttpHeaders header=new HttpHeaders();
		header.set("Authorization", authToken);
		HttpEntity entity=new HttpEntity(header);
		ResponseEntity<String> result = null;
		try {
			result =
		            this.restTemplate.exchange("http://API-GATEWAY/devforum/user/role/",  
		                    HttpMethod.GET, entity, String.class);
		}
		catch(Exception e) {
			return null;
		}
	    return result.getBody();
	}
	public String fallbackForisAdminRole(String auth, Exception e) {
        System.out.println("Admin-App call failed...");
        return null;
    }
	
	public boolean fallbackForisLoggedInUser(String auth, Exception e) {
        System.out.println("Logedin-App call failed...");
        return false;
    }
}
