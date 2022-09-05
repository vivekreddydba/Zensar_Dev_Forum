package com.zensar.df.service;

import java.util.List;

import com.zensar.df.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto createNewCategory(CategoryDto categoryDto, String authToken);
	 List<CategoryDto> GetAllCategories();
	 public List<CategoryDto> getAllCategoriesById(Long id);

}
