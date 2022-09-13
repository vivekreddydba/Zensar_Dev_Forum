package com.zensar.df.Entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.entity.CategoryEntity;

@WebMvcTest(CategoryEntity.class)
public class CategoryEntityTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testCategoryEntity() throws Exception{
		CategoryEntity category=new CategoryEntity();
		category.setId(1);
		category.setName("devform");;
		assertEquals(category.getId(),1);
		assertEquals(category.getName(),"devform");
	}
	
	@Test
	public void testParameterisedCategoryEntity() throws Exception{
		CategoryEntity category=new CategoryEntity(1,"devops");
		assertEquals(category.getId(),1);
		assertEquals(category.getName(),"devops");
	}

}
