package com.zensar.df.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.repo.CategoryRepo;
import com.zensar.df.repo.ForumRepo;

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
    
    @Test
    public void getAllQuestionsByCategoryId() throws Exception {
    	
        ForumRepo forumRepo = mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        ForumDto forumDto = new ForumDto(46,"What is Spring Boot",true,null,37);
        CategoryEntity categoryEntity = new CategoryEntity(37,"Spring");
		List<ForumEntity> forumEntityList = new ArrayList<ForumEntity>();
		forumEntityList.add(new ForumEntity(1,"What is Spring Boot",true,null,categoryEntity));
        when(forumRepo.existsBycategory_id(37)).thenReturn(true);
        when(forumRepo.findBycategory_id(37)).thenReturn(forumEntityList);
        
        List<ForumDto> returnedForumDtoList = forumService.getAllQuestionsByCategoryId(37);
        
        assertEquals("What is Spring Boot", forumDto.getQuestion());
        
    }
   
   @Test
   public void findByTextTest() throws Exception {
   	
       ForumRepo forumRepo = mock(ForumRepo.class);
       forumService.setForumRepo(forumRepo);
       ForumDto forumDto = new ForumDto(46,"What is Spring Boot",true,null,37);
       CategoryEntity categoryEntity = new CategoryEntity(37,"Spring");
       List<ForumEntity> forumEntityList = new ArrayList<ForumEntity>();
       forumEntityList.add(new ForumEntity(1,"What is Spring Boot",true,"Spring boot is backend",categoryEntity));
       when(forumRepo.findByText("sp")).thenReturn(forumEntityList);
       
       List<ForumDto> returnedForumDtoList = forumService.findByText("sp");
       
       assertEquals("What is Spring Boot", forumDto.getQuestion());
       
   }
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
    
    @Test
    public void testUpdateQuestion() throws Exception {
    	ModelMapper mapper= mock(ModelMapper.class);
    	forumService.setMapper(mapper);
    	ForumRepo forumRepo =mock(ForumRepo.class);
        forumService.setForumRepo(forumRepo);
        CategoryRepo categoryRepo =mock(CategoryRepo.class);
        forumService.setCategoryRepo(categoryRepo);
        UserServiceDelegateImpl userServiceDelegate=mock(UserServiceDelegateImpl.class);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", "A1B2C3");
        ForumDto forumDto = new ForumDto(1,"python",true,"development",37,"Akhil");
        CategoryEntity categoryEntity = new CategoryEntity(37,"Spring");
        ForumEntity forumEntity=new ForumEntity(1,"python",true,"development","Akhil",categoryEntity);
        when(forumRepo.getById(1l)).thenReturn(forumEntity);
        when(forumRepo.existsById(1l)).thenReturn(true);
        when(categoryRepo.getById(37l)).thenReturn(categoryEntity);
        when(forumRepo.save(any())).thenReturn(forumEntity);
        ForumDto returnedforumDto = forumService.updateQuestion(1l, forumDto, "A1B2C3");
        
        assertEquals("python", forumDto.getQuestion());
        
    }
}