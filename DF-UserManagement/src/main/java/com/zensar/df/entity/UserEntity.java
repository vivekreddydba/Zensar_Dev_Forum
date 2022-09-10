package com.zensar.df.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="user_name")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="role")
	private String role;
	
	public UserEntity() {}
	public UserEntity(String first_name, String last_name, String username, String password, String email,
			String phone, String role) {
		super();
		this.firstname = first_name;
		this.lastname = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role=role;
	}

	
	public UserEntity(int id, String first_name, String last_name, String username, String password, String email,
			String phone, String role) {
		super();
		this.id = id;
		this.firstname = first_name;
		this.lastname = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role=role;
	}
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", first_name=" + firstname + ", last_name=" + lastname + ", user_name="
				+ username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", role=" + role
				+ "]";
	}
}
