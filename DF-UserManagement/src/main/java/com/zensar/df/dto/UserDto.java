package com.zensar.df.dto;

public class UserDto {
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private long phone;
	
	public UserDto() {
		super();
	}
	
	public UserDto(int id, String firstname, String lastname, String username, String password, String email, long phone) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", phone=" + phone + "]";
	}
	@Override
	public boolean equals(Object obj) {
		UserDto userDto = (UserDto)obj;
		if(this.firstname.equals(userDto.getFirstname()))
			return true;
		return false;
	}

}
