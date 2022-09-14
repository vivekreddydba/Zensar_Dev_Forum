package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.repo.ForumRepo;
import com.zensar.df.service.ForumServiceImpl;

//@WebMvcTest(ForumServiceImpl.class)
public class ForumServiceImplTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ForumServiceImpl forumService;
   
    UserServiceDelegateImpl userServiceDelegate;
    
    @BeforeEach
    public void beforeTestCaseRuns() {
    	forumService = new ForumServiceImpl();
    	userServiceDelegate=new UserServiceDelegateImpl();
    }
    
/*
    @Test
    public void getAllQuestionsByCategoryId() throws Exception {
    	
        ForumRepo forumRepo = mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        ForumDto forumDto = new ForumDto(1,"What is Spring Boot",true,null,23);
		List<ForumEntity> forumEntityList = new ArrayList<ForumEntity>();
		forumEntityList.add(new ForumEntity(1,"What is Spring Boot",true,null,null));
        
        when(forumRepo.findBycategory_id(23)).thenReturn(forumEntityList);
        
        List<ForumDto> returnedForumDtoList = forumService.getAllQuestionsByCategoryId(3);
        
        assertEquals("What is Spring Boot", forumDto.getQuestion());
        
    }
    @Test
    public void testPostNewQuestionCategory() throws Exception{
        ForumRepo forumRepo =mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        ForumServiceImpl forumServiceImpl=mock(ForumServiceImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        ForumDto forumDto=new ForumDto(1,"python",true,"development",3);
        CategoryEntity categoryEntity = new CategoryEntity(3,"Python");
        ForumEntity forumEntity=new ForumEntity(1,"python",true,"development",categoryEntity);
        List<ForumEntity> forumlist=new ArrayList<>();
        forumlist.add(forumEntity);
        when(forumRepo.save(any())).thenReturn(forumlist);
        ForumDto forum = forumService.postNewQuestion(forumDto, "A1B2C3");
        assertEquals("python",forumDto.getAnswers());



   }
   */
    @Test
    public void testDeleteQuestionById() throws Exception{
        ForumRepo forumRepo =mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        ForumDto forumDto=new ForumDto(1,"python",true,"development",3);
        ForumEntity forumEntity=new ForumEntity(1,"python",true,"development");
        List<ForumEntity> forumlist=new ArrayList<>();
        forumlist.add(forumEntity);
        when(forumRepo.existsById(1l)).thenReturn(true);
        boolean response = forumService.deleteQuestionbyId(1l, "A1B2C3");
        assertEquals(true,response);



   }
    /*
    @Test
    public void testUpdateQuestion() throws Exception {
    	ForumRepo forumRepo =mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        ForumDto forumDto = new ForumDto(1,"python",true,"development",3);
        ForumEntity forumEntity=new ForumEntity(1,"python",true,"development");
        List<ForumEntity> forumlist=new ArrayList<>();
        forumlist.add(forumEntity);
        
        when(forumRepo.save(any())).thenReturn(forumlist);
        
        ForumDto returnedforumDto = forumService.updateQuestion(1l, forumDto, "A1B2C3");
        
        assertEquals("python", forumDto.getQuestion());
        
    }
    */
}