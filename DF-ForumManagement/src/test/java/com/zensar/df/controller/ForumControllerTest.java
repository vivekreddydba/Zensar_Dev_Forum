package com.zensar.df.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
//import com.zensar.df.entity.ForumEntity;
import com.zensar.df.service.ForumService;
import com.zensar.df.service.UserServiceDelegate;
import com.zensar.df.utils.JwtUtils;

@WebMvcTest(ForumController.class)
public class ForumControllerTest {
	@Autowired
	MockMvc mockmvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	ForumService forumservice;
	@MockBean
	UserServiceDelegate userServiceDelegate;
	@MockBean
	JwtUtils jwtUtils;
	@MockBean
	ForumDto forumDto;
	
	@Test
	public void DeletebyQuestionId() throws Exception{
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.deleteQuestionbyId(3,"A1B2C3")).thenReturn(true);
		MvcResult mvcResult = this.mockmvc.perform(delete("http://localhost:8001/devforum/question/3")
				        .headers(httpHeaders))
				        .andExpect(status().isOk())
				        .andReturn();
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals("true".equals(response), true);
	}
	
	@Test
	public void DeletebyInvalidQuestionId() throws Exception{
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.deleteQuestionbyId(-1,"A1B2C3")).thenReturn(false);
		MvcResult mvcResult = this.mockmvc.perform(delete("http://localhost:8001/devforum/question/-1")
				        .headers(httpHeaders))
				        .andExpect(status().isBadRequest())
				        .andReturn();
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals("false".equals(response), true);
	}

	@Test
	public void testUpdateQuestion() throws Exception{
		ForumDto f=new ForumDto();
		f.setQuestion("HTML");
        HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		//category.setId(3);
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.updateQuestion(3, f, "A1B2C3")).thenReturn(f);
		MvcResult mvcResult = this.mockmvc.perform(put("http://localhost:8001/devforum/question/3")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(f))
				.headers(httpHeaders)
				).andExpect(status().isOk())
				//.andExpect(content().string(containsString("HTML")))
				.andReturn();
				String response = mvcResult.getResponse().getContentAsString();
				assertEquals(response.contains("HTML"), true);
	}
	@Test
	public void testUpdateQuestionByQuestion() throws Exception{
		ForumDto question=new ForumDto();
		question.setQuestion(null);
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
		question.setQuestionId(3);
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.updateQuestion (question.getQuestionId(), question, "A1B2C3")).thenReturn(question);
		MvcResult mvcResult = this.mockmvc.perform(put("http://localhost:8001/devforum/question/3")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(question))
				.headers(httpHeaders)
				).andExpect(status().isBadRequest())
				.andReturn();
		        
	}
	@Test
	public void postNewQuestionTest() throws Exception{
		ForumDto forum = new ForumDto();
		forum.setQuestion("What is spring");
		forum.setCategoryid(2);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "YD69425");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.postNewQuestion(forum, "YD69425")).thenReturn(forum);
		MvcResult mvcResult = this.mockmvc.perform(post("http://localhost:8001/devforum/question/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(forum))
				.headers(httpHeaders)
				).andExpect(status().isCreated())
				.andExpect(content().string(containsString("spring")))
				.andReturn();
	            String response = mvcResult.getResponse().getContentAsString();
	            System.out.println(response);
	            assertEquals(response.contains("spring"), true);
}
	
	@Test
	public void postNewQuestionByNullQuestionTest() throws Exception{
		ForumDto forum = new ForumDto();
		forum.setQuestion(null);
		forum.setCategoryid(2);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "YD69425");
		when(this.userServiceDelegate.isLoggedInUser(any())).thenReturn(true);
		when(this.forumservice.postNewQuestion(forum, "YD69425")).thenReturn(forum);
		MvcResult mvcResult = this.mockmvc.perform(post("http://localhost:8001/devforum/question/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(forum))
				.headers(httpHeaders)
				).andExpect(status().isBadRequest())
				.andReturn();
}
	
	@Test
	public void getAllQuestionsByCategoryIdTest() throws Exception {
		List<ForumDto> forumDtoList = new ArrayList<ForumDto>();
		forumDtoList.add(new ForumDto(1,"What is Spring Boot",true,null,32));
		when(this.forumservice.getAllQuestionsByCategoryId(32)).
			thenReturn(forumDtoList);
		
		MvcResult mvcResult = this.mockmvc.perform(get("http://localhost:8001/devforum/question/search/category/32"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Spring"), true);
	}
	
	@Test
	public void getAllQuestionsByInvalidCategoryIdTest() throws Exception {
		List<ForumDto> forumDtoList = new ArrayList<ForumDto>();
		forumDtoList.add(new ForumDto(1,"What is Spring Boot",true,null,-1));
		when(this.forumservice.getAllQuestionsByCategoryId(-1)).
			thenReturn(forumDtoList);
		
		MvcResult mvcResult = this.mockmvc.perform(get("http://localhost:8001/devforum/question/search/category/-1"))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
			@Test
		    public void addSearchTestVaild() throws Exception   {
		        List<ForumDto> searchText=new ArrayList<>();
		        searchText.add(new ForumDto());
		        when(this.forumservice.findByText("is")).thenReturn(searchText);
		        MvcResult mvcResult = this.mockmvc.perform(get("http://localhost:8001/devforum/question/search/is")
		        		.contentType("application/json"))
		        		.andExpect(status().isOk())
		                .andReturn();      
		    }
			
			@Test
		    public void addSearchTestvaildForJacoco() throws Exception   {
		        List<ForumDto> searchText=new ArrayList<>();
		        
		        searchText.add(new ForumDto());
		        when(this.forumservice.findByText("jacoco")).thenReturn(searchText);
		        MvcResult mvcResult = this.mockmvc.perform(get("http://localhost:8001/devforum/question/search/jacoco")
		        		.contentType("application/json"))
		                .andExpect(status().isOk())
		                .andReturn();
			}	
}

