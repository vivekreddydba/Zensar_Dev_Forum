package com.zensar.df.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryId")
	private long id;
	@Column(name = "categoryName")
	private String name;
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
