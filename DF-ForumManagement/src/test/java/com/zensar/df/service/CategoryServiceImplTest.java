package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.repo.CategoryRepo;
public class CategoryServiceImplTest {
	
	@Autowired
    MockMvc mockMvc;
    
    CategoryServiceImpl categoryService;
	
	 @BeforeEach
	    public void beforeTestCaseRuns() {
	    	categoryService = new CategoryServiceImpl();
	    }
	@Test
    public void getAllCategoriesTest() throws Exception {
    	
        CategoryRepo categoryRepo = mock(CategoryRepo.class);
        categoryService.setCategoryRepo(categoryRepo);
        CategoryDto categoryDto = new CategoryDto(1,"What is Spring Boot");
		List<CategoryEntity> categoryEntityList = new ArrayList<CategoryEntity>();
		categoryEntityList.add(new CategoryEntity(1,"What is Spring Boot"));
        
        when(categoryRepo.findAll()).thenReturn(categoryEntityList);
        
        List<CategoryDto> returnedCategoryDtoList = categoryService.GetAllCategories();
        
        assertEquals("What is Spring Boot", categoryDto.getName());
        
    }
	
	@Test
    public void getAllCategoriesByIdTest() throws Exception {
    	
        CategoryRepo categoryRepo = mock(CategoryRepo.class);
        categoryService.setCategoryRepo(categoryRepo);
        CategoryDto categoryDto = new CategoryDto(1,"Spring");
        CategoryEntity categoryentity = new CategoryEntity(1,"Spring");
		Optional<CategoryEntity> categoryEntity = Optional.of(categoryentity);
        long id = 1;
        when(categoryRepo.findById(id)).thenReturn(categoryEntity);
        
        CategoryDto returnedCategoryDto = categoryService.getAllCategoriesById(id);
        
        assertEquals("Spring", categoryDto.getName());
        
    }
	
	
	

}
