package com.zensar.df.dto;

import java.util.Objects;

public class CategoryDto {
	public CategoryDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CategoryDto() {
		super();
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
		return "CategoryDto [id=" + id + ", name=" + name + "]";
	}
	private long id;
	private String name;
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDto other = (CategoryDto) obj;
		return Objects.equals(name, other.name);
	}

}
