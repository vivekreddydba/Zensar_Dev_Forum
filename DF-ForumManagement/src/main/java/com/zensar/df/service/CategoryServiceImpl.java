package com.zensar.df.service;

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
}
