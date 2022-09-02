package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
@Service
public class CategoryServiceImpl implements CategoryService {
	int lastCategoryId=0;
	@Override
	public CategoryDto createNewCategory(CategoryDto categoryDto) {
		lastCategoryId = lastCategoryId+1;
		categoryDto.setId(lastCategoryId);
		return new CategoryDto(categoryDto.getId(),categoryDto.getName());
	}
	
	@Override
	public List<CategoryDto> GetAllCategories() {
		categories.add(new CategoryDto(1,"Devops"));
		categories.add(new CategoryDto(2,"Java"));
		return categories;
	}
	
	List<CategoryDto> categories = new ArrayList<CategoryDto>();
}
