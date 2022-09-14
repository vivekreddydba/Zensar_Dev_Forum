package com.zensar.df.Entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.ForumEntity;

@WebMvcTest(ForumEntity.class)
public class ForumEntityTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testForumEntity() throws Exception{
		ForumEntity forum=new ForumEntity();
		//forum.setQuestionId(1);
		forum.setQuestion("devops");
		forum.setAnswers("development");
		//forum.setCategoryid(5);
		forum.setStatus(true);
		assertEquals(forum.getQuestion(),"devops");
	}
	
	
	@Test
	public void testParameterisedForumEntity() throws Exception{
		ForumEntity forum=new ForumEntity(1,"what is c",true,"development");
		assertEquals(forum.getQuestion(),"what is c");
		//assertEquals(forum.getCategoryid(),2);
		assertEquals(forum.getAnswers(),"development");
		assertEquals(forum.isStatus(),true);
		
	}

}
