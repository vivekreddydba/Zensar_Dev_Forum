package com.zensar.df.service;

public interface UserServiceDelegate {
	public boolean isLoggedInUser(String authToken);

	public String isAdminRole(String auth);
	
	public String hasUserName(String auth);


}
