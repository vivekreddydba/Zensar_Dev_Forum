package com.zensar.df.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "Category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private long id;
	@Column(name = "category_name")
	private String name;
	
	@OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "category")
	private Set<ForumEntity> questions;
	public CategoryEntity() {
		super();
	}
	public CategoryEntity(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<ForumEntity> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<ForumEntity> questions) {
		this.questions = questions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + "]";
	}
 
}
