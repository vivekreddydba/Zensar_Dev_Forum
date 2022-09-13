package com.zensar.df.Dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.ForumDto;

@WebMvcTest(ForumDto.class)
public class DtoTest {
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testForumDto() throws Exception{
		ForumDto forum=new ForumDto();
		forum.setQuestionId(1);
		forum.setQuestion("devops");
		forum.setAnswers("development");
		forum.setCategoryid(5);
		forum.setStatus(true);
		assertEquals(forum.getQuestionId(),1);
	}
	
	
	@Test
	public void testParameterisedForumDto() throws Exception{
		ForumDto forum=new ForumDto("devops",true,"development",2);
		assertEquals(forum.getQuestion(),"devops");
		assertEquals(forum.getCategoryid(),2);
		assertEquals(forum.getAnswers(),"development");
		assertEquals(forum.isStatus(),true);
		
	}
	
	@Test
	public void testParameterisedForumDto1() throws Exception{
		ForumDto forum = new ForumDto(1,"what is docker");
		assertEquals(forum.getQuestionId(),1);
		assertEquals(forum.getQuestion(),"what is docker");
	}
	

}
