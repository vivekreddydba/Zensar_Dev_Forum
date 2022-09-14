package com.zensar.df.Dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;


@WebMvcTest(CategoryDto.class)
public class CategoryDtoTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testCategoryDto() throws Exception {
		CategoryDto category = new CategoryDto();
		category.setName("c");
		assertEquals(category.getName(),"c");
	}
	
	@Test
	public void testParameterisedDto() throws Exception {
		CategoryDto category = new CategoryDto("c");
		assertEquals(category.getName(),"c");
	}
	
	@Test
	public void testParameterisedDto1() throws Exception{
		CategoryDto category = new CategoryDto(1,"java");
		assertEquals(category.getId(),1);
		assertEquals(category.getName(),"java");
		
	}
	
	@Test
	void testCandidateDtoEquals1() {
	    CategoryDto user1 =  new CategoryDto(1,"what is c");
	    CategoryDto user2 =  new CategoryDto(1,"what is c");
	    //RegisterUser user2 =  new RegisterUser("anand","kulkarni","anand","anand123","anand@gmail.com","9999999999");
	assertTrue(user1.equals(user2));
	}

}
