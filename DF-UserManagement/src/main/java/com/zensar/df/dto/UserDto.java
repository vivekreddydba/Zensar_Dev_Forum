package com.zensar.df.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="User model has information about user")
public class UserDto {
	@ApiModelProperty(value="User ID")
	private int id;
	@ApiModelProperty(value="Firstname of the user")
	private String firstname;
	@ApiModelProperty(value="Lastname of the user")
	private String lastname;
	@ApiModelProperty(value="usrname of the user for login")
	private String username;
	@ApiModelProperty(value="Password of the user for login")
	private String password;
	@ApiModelProperty(value="Email address of the user")
	private String email;
	@ApiModelProperty(value="Phone Number of the user")
	private long phone;
	@ApiModelProperty(value="Role")
	private String role;
	
	public UserDto() {
		super();
	}
	public UserDto(int id, String firstname, String lastname, String username, String password, String email, long phone, String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role=role;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", phone=" + phone + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		UserDto userDto = (UserDto)obj;
		if(this.firstname.equals(userDto.getFirstname()))
			return true;
		return false;
	}

}
