package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.repo.CategoryRepo;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CategoryEntity categoryEntity;
	@Autowired
	ModelMapper mapper;
	int lastCategoryId=0;
	@Override
	public CategoryDto createNewCategory(CategoryDto categoryDto, String authToken) {
		lastCategoryId = lastCategoryId+1;
		categoryDto.setId(lastCategoryId);
		CategoryEntity categoryEntity = new CategoryEntity(categoryDto.getId(),categoryDto.getName());
		categoryEntity = categoryRepo.save(categoryEntity);
		return new CategoryDto(categoryDto.getId(),categoryDto.getName());
	}
	
	@Override
	public List<CategoryDto> GetAllCategories() {
		List<CategoryEntity> categoryEntities = categoryRepo.findAll();
		return categoryEntities.stream().map(i -> mapper.map(i, CategoryDto.class)).collect(Collectors.toList());
	}
	

	@Override
	public List<CategoryDto> getAllCategoriesById(Long id) {
		Optional<CategoryEntity> categoryEntities = categoryRepo.findById(id);
		return categoryEntities.stream().map(i -> mapper.map(i, CategoryDto.class)).collect(Collectors.toList());
		
	}
}
