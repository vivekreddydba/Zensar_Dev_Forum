package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.repo.ForumRepo;
import com.zensar.df.service.ForumServiceImpl;

//@WebMvcTest(ForumServiceImpl.class)
public class ForumServiceImplTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ForumServiceImpl forumService;
    
    @BeforeEach
    public void beforeTestCaseRuns() {
    	forumService = new ForumServiceImpl();
    }
    
//    @Test
//    public void getAllQuestionsByCategoryId() throws Exception {
//    	
//        ForumRepo forumRepo = mock(ForumRepo.class);
//        forumService.setForumRepo(forumRepo);
//        ForumDto forumDto = new ForumDto(1,"What is Spring Boot",true,null,23);
//		List<ForumEntity> forumEntityList = new ArrayList<ForumEntity>();
//		forumEntityList.add(new ForumEntity(1,"What is Spring Boot",true,null,null));
//        
//        when(forumRepo.findBycategory_id(23)).thenReturn(forumEntityList);
//        
//        List<ForumDto> returnedForumDtoList = forumService.getAllQuestionsByCategoryId(3);
//        
//        assertEquals("What is Spring Boot", forumDto.getQuestion());
//        
//    }
    
    
    
    
    
    
    
}


