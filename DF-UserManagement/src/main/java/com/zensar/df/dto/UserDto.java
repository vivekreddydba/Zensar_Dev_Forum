package com.zensar.df.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="User model has information about user")
public class UserDto {
	@ApiModelProperty(value="User ID")
	private int id;
	@NotEmpty
	@Size(min =4, message = "user name should have at least 2 characters")
	@ApiModelProperty(value="Firstname of the user")
	private String firstname;
	@NotEmpty
	@Size(min =4, message = "user name should have at least 2 characters")
	@ApiModelProperty(value="Lastname of the user")
	private String lastname;
	@NotEmpty
	@Size(min =4, message = "user name should have at least 2 characters")
	@ApiModelProperty(value="usrname of the user for login")
	private String username;
	@ApiModelProperty(value="Password of the user for login")
	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	private String password;
	@ApiModelProperty(value="Email address of the user")
	@NotEmpty
	@Email
	private String email;
	@ApiModelProperty(value="Phone Number of the user")
	@NotEmpty
	@Size(min =10, max=10, message = "Phone number should have at least 10 characters")
	private String phone;
	@NotEmpty
	@ApiModelProperty(value="Role")
	private String role;
	
	public UserDto() {
		super();
	}
	public UserDto(int id, String firstname, String lastname, String username, String password, String email, String phone, String role) {
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
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
