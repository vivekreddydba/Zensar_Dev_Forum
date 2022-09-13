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
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidCategoryIdException;
import com.zensar.df.exception.InvalidRoleException;
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
	
	
	@Override
	public CategoryDto createNewCategory(CategoryDto categoryDto, String authToken) {
		
		if (!userServiceDelegate.isLoggedInUser(authToken)) {

			throw new InvalidAuthorizationTokenException(authToken);
		}
		
		if (!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(authToken)))) {

			throw new InvalidRoleException("" + "User Not Allowed");
		}
		categoryEntity = mapper.map(categoryDto, CategoryEntity.class);
		categoryEntity = categoryRepo.save(categoryEntity);
		categoryDto = mapper.map(categoryEntity,CategoryDto.class);
		return new CategoryDto(categoryDto.getId(),categoryDto.getName());
	}
	
//	@Override
//	public List<CategoryDto> GetAllCategories() {
//		List<CategoryEntity> categoryEntities = categoryRepo.findAll();
//		return categoryEntities.stream().map(i -> mapper.map(i, CategoryDto.class)).collect(Collectors.toList());
//	}
	public List<CategoryDto> GetAllCategories(){
		List<CategoryEntity> categoryEntityList = categoryRepo.findAll();
		List<CategoryDto> categoryDtoList = new ArrayList<>();
		for(CategoryEntity categoryEntity: categoryEntityList) {
			CategoryDto category = 
					new CategoryDto(categoryEntity.getId(),categoryEntity.getName());
			categoryDtoList.add(category);
		}
		return categoryDtoList;
	}
	

	@Override
	public CategoryDto getAllCategoriesById(Long id) {
		Optional<CategoryEntity> opcategoryEntities = categoryRepo.findById(id);
		if(opcategoryEntities.isPresent()) {
			CategoryEntity categoryEntity = opcategoryEntities.get();
		
			//return opcategoryEntities.stream().map(i -> mapper.map(i, CategoryDto.class)).collect(Collectors.toList());
			return new CategoryDto(categoryEntity.getId(), categoryEntity.getName());
			
		}
		throw new InvalidCategoryIdException("Catgory Id is not found:"+id);
		
	}
	@Override
    public boolean DeleteCategoryById(Long id, String  auth){
        
        if (!userServiceDelegate.isLoggedInUser(auth)) {



           throw new InvalidAuthorizationTokenException(auth);
        }
        
        if (!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(auth)))) {



           throw new InvalidRoleException(""+"User Not Allowed");
        }



       if(categoryRepo.existsById(id)) {
            CategoryEntity temp=categoryRepo.getById(id);
            categoryRepo.delete(temp);
            return true;
        }
        
        return false;
        
                
    }
	

	@Override
	public CategoryDto updateCategory(long categoryId, CategoryDto category, String auth){
		
		if(!userServiceDelegate.isLoggedInUser(auth)) {
			
			throw new InvalidAuthorizationTokenException(auth);
		}
		
		if(!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(auth)))) {
			
			throw new InvalidRoleException("");
		}
	
		
		CategoryEntity categoryEntity = categoryRepo.getById(categoryId);
		if(categoryRepo.existsById(categoryId)){

			categoryEntity.setName(category.getName());

            CategoryEntity updatedCategory = categoryRepo.save(categoryEntity);

            return new CategoryDto(updatedCategory.getId(), updatedCategory.getName());
        }
		throw new InvalidCategoryIdException(""+categoryId);
	}
	public void setCategoryRepo(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
}
