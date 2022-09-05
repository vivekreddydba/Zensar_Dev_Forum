package com.zensar.df.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.CategoryDto;
import com.zensar.df.service.CategoryService;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CategoryService categoryService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testGetAllCategories() throws Exception {
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		categoryDtoList.add(new CategoryDto(1,"python"));
		when(this.categoryService.GetAllCategories()).
		thenReturn(categoryDtoList);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("python"), true);
	}
		
		@Test
		public void test2GetAllCategories() throws Exception {
			List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
			when(this.categoryService.GetAllCategories()).
			thenReturn(categoryDtoList);
			
			MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category"))
					.andExpect(status().isOk())
					.andReturn();
			String response = mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("[]"), true);

}
	
	@Test
	public void testgetAllCategoriesById() throws Exception {
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		categoryDtoList.add(new CategoryDto(1,"Spring Boot"));
		long val = 1;
		when(this.categoryService.getAllCategoriesById(val)).
			thenReturn(categoryDtoList);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category/1"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Spring Boot"), true);
	}
	
	@Test
	public void test2getAllCategoriesById() throws Exception {
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		categoryDtoList.add(new CategoryDto(1,"Spring Boot"));
		long val = -1;
		when(this.categoryService.getAllCategoriesById(val)).
			thenReturn(categoryDtoList);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category/1"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("[]"), true);
	}
}
