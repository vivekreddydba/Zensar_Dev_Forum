package com.zensar.df.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Category model has information about category")
@Component
public class CategoryDto {
	public CategoryDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CategoryDto( String name) {
		super();
		//this.id = id;
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
	@Schema(description="Category ID")
	private long id;
	@Schema(description="Category Name")
	@NotEmpty
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
