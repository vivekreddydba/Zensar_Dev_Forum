package com.zensar.df.Entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void testForumEntityByQuestion() throws Exception{
		ForumEntity forum=new ForumEntity();
		forum.setQuestion("devforum");
		forum.setStatus(false);
		assertEquals(forum.isStatus(),false);
		
	}
	
	@Test
	public void testForumEntityByAnswer() throws Exception{
		ForumEntity forum=new ForumEntity();
		forum.setAnswers("project");
		forum.setUsername("xyz");
		assertEquals(forum.getUsername(),"xyz");
	}
	@Test
	public void testForumEntityByLongParameterised() throws Exception{
		ForumEntity forum=new ForumEntity();
		forum.setQuestionid(0);
		assertEquals(forum.getQuestionid(),0);
		
	}
	@Test 
	public void testForumEntityanswers() throws Exception{
		ForumEntity forum1=new ForumEntity();
		ForumEntity forum2=new ForumEntity();
		forum1.setAnswers("xyz");
		forum2.setAnswers("xyz");
		assertFalse(forum1.equals(forum2));
	}
	
	@Test
	public void testParameterisedForumEntity() throws Exception{
		ForumEntity forum=new ForumEntity(1,"what is c",true,"development");
		assertEquals(forum.getQuestion(),"what is c");
		//assertEquals(forum.getCategoryid(),2);
		assertEquals(forum.getAnswers(),"development");
		assertEquals(forum.isStatus(),true);
		
	}
	
	@Test
	public void testForumString() throws Exception{
		ForumEntity forum=new ForumEntity(1,"xyz",false,"devops");
		forum.equals(forum);
	}
	
	@Test
	public void testParameterisedForumEntityEquals() throws Exception{
		ForumEntity forum1=new ForumEntity(1,"what is c",true,"development");
		ForumEntity forum2=new ForumEntity(1,"what is c",true,"development");
		assertFalse(forum1.equals(forum2));
		
	}

}
