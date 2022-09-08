package com.zensar.df.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidCategoryIdException;
import com.zensar.df.repo.CategoryRepo;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CategoryEntity categoryEntity;
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserServiceDelegate userServiceDelegate;
	
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
		Optional<CategoryEntity> opcategoryEntities = categoryRepo.findById(id);
		if(opcategoryEntities.isPresent()) {
			CategoryEntity categoryEntity = opcategoryEntities.get();
		
			return opcategoryEntities.stream().map(i -> mapper.map(i, CategoryDto.class)).collect(Collectors.toList());
			
		}
		throw new InvalidCategoryIdException("Catgory Id is not found:"+id);
		
	}
	@Override
	public ResponseEntity<Boolean> DeleteCategoryById(Long id){
		if(categoryRepo.existsById(id)) {
			CategoryEntity temp=categoryRepo.getById(id);
			categoryRepo.delete(temp);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		
				
	}
	
	@Override
	public CategoryDto updateCategory(long categoryId, CategoryDto category, String auth) {
		
		if(!userServiceDelegate.isLoggedInUser(auth)) {
			
			throw new InvalidAuthorizationTokenException(auth);
		}
		
		CategoryEntity categoryEntity = categoryRepo.getById(categoryId);
	
		if(categoryRepo.existsById(categoryId)){

			categoryEntity.setName(category.getName());

            CategoryEntity updatedCategory = categoryRepo.save(categoryEntity);

            return new CategoryDto(updatedCategory.getId(), updatedCategory.getName());
        }
        
		throw new InvalidCategoryIdException(""+categoryId);
	}
}
