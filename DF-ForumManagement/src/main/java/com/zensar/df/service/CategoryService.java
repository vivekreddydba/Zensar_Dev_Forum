package com.zensar.df.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zensar.df.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto createNewCategory(CategoryDto categoryDto, String authToken);
	 List<CategoryDto> GetAllCategories();
	 public CategoryDto getAllCategoriesById(Long id);
	 CategoryDto updateCategory(long id, CategoryDto category, String auth);
	 public boolean DeleteCategoryById(Long id,String auth);

}
