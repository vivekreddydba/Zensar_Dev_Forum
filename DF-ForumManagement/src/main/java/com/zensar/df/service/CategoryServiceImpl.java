package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.repo.CategoryRepo;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	int lastCategoryId=0;
	@Override
	public CategoryDto createNewCategory(CategoryDto categoryDto) {
		lastCategoryId = lastCategoryId+1;
		categoryDto.setId(lastCategoryId);
		return new CategoryDto(categoryDto.getId(),categoryDto.getName());
	}
	
	@Override
	public List<CategoryDto> GetAllCategories() {
		//categories.add(new CategoryDto(1,"Devops"));
		//categories.add(new CategoryDto(2,"Java"));
		categoryRepo.findAll();
		return categories;
	}
	
	List<CategoryDto> categories = new ArrayList<CategoryDto>();
}
