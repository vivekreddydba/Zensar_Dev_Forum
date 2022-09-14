package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.repo.CategoryRepo;
import com.zensar.df.repo.ForumRepo;
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
	@Test
    public void testDeleteCategoryById() throws Exception{
        CategoryRepo categoryRepo =mock(CategoryRepo.class);
        categoryService.setCategoryRepo(categoryRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        CategoryDto categoryDto=new CategoryDto(1,"python");
        CategoryEntity categoryEntity=new CategoryEntity(1,"python");
        when(categoryRepo.existsById(1l)).thenReturn(true);
        boolean response = categoryService.DeleteCategoryById(1l, "A1B2C3");
        assertEquals(true,response);

   }
	
	@Test
    public void testUpdateCategory() throws Exception {
    	CategoryRepo categoryRepo =mock(CategoryRepo.class);
        categoryService.setCategoryRepo(categoryRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        CategoryDto categoryDto = new CategoryDto(1,"python");
        CategoryEntity categoryEntity = new CategoryEntity(37,"Spring");
        when(categoryRepo.getById(1l)).thenReturn(categoryEntity);
        when(categoryRepo.existsById(1l)).thenReturn(true);
        when(categoryRepo.save(any())).thenReturn(categoryEntity);
        CategoryDto returnedcategoryDto = categoryService.updateCategory(1l, categoryDto, "A1B2C3");
        
        assertEquals("python", categoryDto.getName());
        
    }
	/*
	@Test
    public void testCreateNewCategory() throws Exception {
		ModelMapper mapper= mock(ModelMapper.class);
    	categoryService.setMapper(mapper);
    	CategoryRepo categoryRepo =mock(CategoryRepo.class);
        categoryService.setCategoryRepo(categoryRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        CategoryDto categoryDto = new CategoryDto(1,"python");
        CategoryEntity categoryEntity = new CategoryEntity(1,"python");
        when(categoryRepo.save(any())).thenReturn(categoryEntity);
        CategoryDto returnedcategoryDto = categoryService.createNewCategory(categoryDto, "A1B2C3");
        
        assertEquals("python", categoryDto.getName());
        
    }
    */
	

}
