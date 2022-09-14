package com.zensar.df.entity;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BlackListEntity.class)
public class TestBlacklistEntity {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserDetailsService userService;
	
	@Test
	public void testBlackListEntity() throws Exception{
		BlackListEntity blist=new BlackListEntity();
		blist.setCreateddate(LocalDate.now());
		blist.setToken("A1B2C3");
		assertEquals(blist.getCreateddate(), LocalDate.now());
	}
	
	@Test
	public void testBlackListEntityByToken() throws Exception{
		BlackListEntity blist=new BlackListEntity();
		blist.setCreateddate(LocalDate.now());
		blist.setToken("A1B2C3");
		blist.setId(1);
		assertEquals("A1B2C3", blist.getToken());
	}
	
	@Test
	public void testBlackListEntityParameterized() throws Exception{
		BlackListEntity blist=new BlackListEntity(1,"A1B2C3",LocalDate.now());
		assertEquals(blist.getCreateddate(),LocalDate.now());
		assertEquals("A1B2C3", blist.getToken());
		assertEquals(1,blist.getId());
	}

}
