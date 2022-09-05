package com.zensar.df.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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
		categoryDtoList.add(new CategoryDto(1,"Devops"));
		categoryDtoList.add(new CategoryDto(2,"Python"));
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8001/devforum/category"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Devops"), true);

}
}
