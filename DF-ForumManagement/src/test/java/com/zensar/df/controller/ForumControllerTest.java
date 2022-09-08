package com.zensar.df.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.ForumDto;
//import com.zensar.df.entity.ForumEntity;
import com.zensar.df.service.ForumService;

@WebMvcTest(ForumController.class)
public class ForumControllerTest {
	@Autowired
	MockMvc mockmvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	ForumService forumservice;
	@MockBean
	ForumDto forumDto;
	
	@Test
	public void DeletebyQuestionId() throws Exception{
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "A1B2C3");
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
		when(this.forumservice.deleteQuestionbyId(-1,"A1B2C3")).thenReturn(false);
		MvcResult mvcResult = this.mockmvc.perform(delete("http://localhost:8001/devforum/question/-1")
				        .headers(httpHeaders))
				        .andExpect(status().isBadRequest())
				        .andReturn();
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals("false".equals(response), true);
	}

}
