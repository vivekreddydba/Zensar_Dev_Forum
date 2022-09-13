package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidCategoryIdException;
import com.zensar.df.exception.InvalidQuestionIdException;
import com.zensar.df.exception.InvalidRoleException;
import com.zensar.df.repo.CategoryRepo;
import com.zensar.df.repo.ForumRepo;

@Service
public class ForumServiceImpl implements ForumService{
    @Autowired
    ForumRepo forumRepo;
    @Autowired
    CategoryDto categoryDto;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ForumDto forumDto;
    @Autowired
    ForumEntity forumEntity;
    
    @Autowired
    ForumRepo forumrepo;
    @Autowired
    CategoryEntity categoryEntity;
    
    @Autowired
	UserServiceDelegate userServiceDelegate;
    
	//String question=forumDto.getQuestion();
	@Override
	public ForumDto postNewQuestion(ForumDto forumDto, String authToken) {
		if (!userServiceDelegate.isLoggedInUser(authToken)) {

			throw new InvalidAuthorizationTokenException(authToken);
		}
		forumEntity = mapper.map(forumDto,ForumEntity.class);
		CategoryEntity categoryEntity = categoryRepo.getById(forumDto.getCategoryid());
		forumEntity.setCategory(categoryEntity);
		forumEntity.setStatus(true);
		forumEntity = forumRepo.save(forumEntity);
		forumDto = mapper.map(forumEntity,ForumDto.class);
		forumDto.setCategoryid(forumEntity.getCategory().getId());
		return forumDto;
		//return new ForumDto(forumDto.getQuestionId(),forumDto.getQuestion(),true,forumDto.getAnswers());
	}
	
	@Override
	public boolean deleteQuestionbyId(long questionId,String auth) {
       if(!userServiceDelegate.isLoggedInUser(auth)) {
			
			throw new InvalidAuthorizationTokenException(auth);
       }
		
		if(forumrepo.existsById(questionId)) {
			forumrepo.deleteById(questionId);
		    return true;
		}
		return false;
				
		
	}

	
	@Override
    public ForumDto updateQuestion(long questionId, ForumDto forum, String auth) {
        
        if(!userServiceDelegate.isLoggedInUser(auth)) {
            
            throw new InvalidAuthorizationTokenException(auth);
        }
      //  ForumEntity forumEntity = mapper.map(forumDto,ForumEntity.class);
        ForumEntity forumEntity = forumRepo.getById(questionId);



       if(forumRepo.existsById(questionId)){

    	   CategoryEntity categoryEntity = categoryRepo.getById(forum.getCategoryid());
    	   forumEntity.setCategory(categoryEntity);
           //ForumDto forumDto = mapper.map(forumEntity,ForumDto.class);
            forumEntity.setQuestion(forum.getQuestion());
            forumEntity.setStatus(forum.isStatus());
            ForumEntity forumEntity1 = mapper.map(forumDto,ForumEntity.class);
           ForumEntity updatedquestion = forumRepo.save(forumEntity);
           return new ForumDto(updatedquestion.getQuestionid(), updatedquestion.getQuestion(),updatedquestion.isStatus(),updatedquestion.getAnswers(),updatedquestion.getCategory().getId());
        }
        
        throw new InvalidQuestionIdException(""+questionId);
    }
	
	
	public List<ForumDto> getAllQuestionsByCategoryId(long categoryid){
		if(forumRepo.existsBycategory_id(categoryid)) {
		List<ForumEntity> forumEntityList = forumRepo.findBycategory_id(categoryid);
		List<ForumDto> forumDtoList = new ArrayList<>();
		for(ForumEntity forumEntity: forumEntityList) {
			forumEntity.setStatus(true);
			ForumDto forum = 
					new ForumDto(forumEntity.getQuestionid(),forumEntity.getQuestion(),forumEntity.isStatus(),forumEntity.getAnswers(),forumEntity.getCategory().getId());
			forumDtoList.add(forum);
		}
		return forumDtoList;
		}
		throw new InvalidCategoryIdException("Category Id Not Found"+categoryid);
	}
	}




