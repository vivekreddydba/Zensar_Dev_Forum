package com.zensar.df.model;



import java.time.LocalDate;
import java.time.LocalDateTime;


//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;

@Entity
public class User {

	
	@Id
	@Column(name="ID")
	private String id;
	private String username;
	private String password;
	private String role;
	
    
	
	public String getId() {
		return id;
	}
	
	
	

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}






	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


    	
}

