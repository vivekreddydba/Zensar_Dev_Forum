package com.zensar.df.entity;

import java.time.LocalDate;
//import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Blacklist")

public class BlackListEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="token")
	private String token;
	@Column(name="Created_date")
	private LocalDate createddate;
	public BlackListEntity() {}
	public BlackListEntity( String token, LocalDate createddate) {
		super();
		this.token = token;
		this.createddate = createddate;
	}
	
	public BlackListEntity(int id, String token, LocalDate createddate) {
		super();
		this.id = id;
		this.token = token;
		this.createddate = createddate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDate createddate) {
		this.createddate = createddate;
	}
	@Override
	public String toString() {
		return "BlackListEntity [id=" + id + ", token=" + token + ", createddate=" + createddate + "]";
	}
	
	

}
