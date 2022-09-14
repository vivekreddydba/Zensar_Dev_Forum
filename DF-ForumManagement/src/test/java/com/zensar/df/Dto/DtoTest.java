package com.zensar.df.Dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	
//	@Test
//	public void testParameterisedForumDto() throws Exception{
//		ForumDto forum=new ForumDto("devops",true,"development",2);
//		assertEquals(forum.getQuestion(),"devops");
//		assertEquals(forum.getCategoryid(),2);
//		assertEquals(forum.getAnswers(),"development");
//		assertEquals(forum.isStatus(),true);
//		
//	}
//	
	@Test
	public void testParameterisedForumDto1() throws Exception{
		ForumDto forum = new ForumDto(1,"what is docker");
		assertEquals(forum.getQuestionId(),1);
		assertEquals(forum.getQuestion(),"what is docker");
	}
	
	@Test
	void testCandidateDtoEquals1() {
	    ForumDto user1 =  new ForumDto(1,"what is c",true,"language",3);
	    ForumDto user2 =  new ForumDto(1,"what is c",true,"language",3);
	    //RegisterUser user2 =  new RegisterUser("anand","kulkarni","anand","anand123","anand@gmail.com","9999999999");
	assertTrue(user1.equals(user2));
	}
//	@Test
//	void testCandidateDtoEquals2() {
//		ForumDto user1 =  new ForumDto(1,"what is c",true,"language",3);
//	    AuthenticationRequest auth=new AuthenticationRequest("username","password","ROLE_ADMIN");
//	assertFalse(user1.equals(auth));
//	}
	

}
