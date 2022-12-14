package com.zensar.df.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.service.CategoryService;
import com.zensar.df.service.UserServiceDelegate;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CategoryService categoryService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	UserServiceDelegate userServiceDelegate;
	
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
		CategoryDto categoryDto =new CategoryDto(1,"Spring Boot");
		long val = 1;
		when(this.categoryService.getAllCategoriesById(val)).
			thenReturn(categoryDto);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category/1"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Spring Boot"), true);
	}
	
	@Test
	public void test2getAllCategoriesById() throws Exception {
		CategoryDto categoryDto =new CategoryDto(1,"Spring Boot");
		long val = -1;
		when(this.categoryService.getAllCategoriesById(val)).
			thenReturn(categoryDto);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category/1"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains(""), true);
	}
	
	@Test
	public void testcreateNewCategory() throws Exception{
		CategoryDto category = new CategoryDto();
		category.setName("Jenkins & Devops");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "YD69425");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
        when(this.userServiceDelegate.isAdminRole(any())).thenReturn("ROLE_ADMIN");
		when(this.categoryService.createNewCategory(category, "YD69425")).thenReturn(category);
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:8001/devforum/category/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category))
				.headers(httpHeaders)
				).andExpect(status().isCreated())
				.andExpect(content().string(containsString("Devops")))
				.andReturn();
	            String response = mvcResult.getResponse().getContentAsString();
	            System.out.println(response);
	            assertEquals(response.contains("Devops"), true);
	}
	@Test
	public void test2createNewCategory() throws Exception{
		CategoryDto category = new CategoryDto();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "YD69425");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
        when(this.userServiceDelegate.isAdminRole(any())).thenReturn("ROLE_ADMIN");
		when(this.categoryService.createNewCategory(category, "YD69425")).thenReturn(category);
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:8001/devforum/category/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category))
				.headers(httpHeaders)
				).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("")))
				.andReturn();
				String response = mvcResult.getResponse().getContentAsString();
				assertEquals(response.contains(""), true);
	}
	@Test
	public void testUpdateCategory() throws Exception{
		CategoryDto category=new CategoryDto();
		category.setName("HTML");
        HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		category.setId(3);
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
        when(this.userServiceDelegate.isAdminRole(any())).thenReturn("ROLE_ADMIN");
		when(this.categoryService.updateCategory(category.getId(), category, "A1B2C3")).thenReturn(category);
		MvcResult mvcResult = this.mockMvc.perform(put("http://localhost:8001/devforum/category/3")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category))
				.headers(httpHeaders)
				).andExpect(status().isOk())
				.andExpect(content().string(containsString("HTML")))
				.andReturn();
				String response = mvcResult.getResponse().getContentAsString();
				assertEquals(response.contains("HTML"), true);
	}
	@Test
	public void testUpdateCategoryByName() throws Exception{
		CategoryDto category=new CategoryDto();
		category.setName("");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		category.setId(3);
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.categoryService.updateCategory(category.getId(), category, "A1B2C3")).thenReturn(category);
		MvcResult mvcResult = this.mockMvc.perform(put("http://localhost:8001/devforum/category/3")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category))
				.headers(httpHeaders)
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	@Test
    public void DeletebyCategoryIdTest() throws Exception{
        CategoryDto category=new CategoryDto();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        category.setId(2);
        when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
        when(this.userServiceDelegate.isAdminRole(any())).thenReturn("ROLE_ADMIN");
        when(this.categoryService.DeleteCategoryById(category.getId(),"A1B2C3")).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(delete("http://localhost:8001/devforum/category/2")
                        .headers(httpHeaders))
                        .andExpect(status().isOk())
                        .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("true".equals(response), true);
    }
    
    @Test
    public void DeletebyInvalidCategoryIdTest() throws Exception{
        CategoryDto category=new CategoryDto();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        category.setId(2);
        when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
        when(this.userServiceDelegate.isAdminRole(any())).thenReturn("ROLE_ADMIN");
        when(this.categoryService.DeleteCategoryById(category.getId(),"A1B")).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(delete("http://localhost:8001/devforum/category/2")
                        .headers(httpHeaders))
                        .andExpect(status().isBadRequest())
                        .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("false".equals(response), true);
    }
	
}   
