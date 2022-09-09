package com.zensar.df.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidCategoryIdException;
import com.zensar.df.exception.InvalidqusIdException;
import org.springframework.stereotype.Service;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.repo.ForumRepo;

@Service
public class ForumServiceImpl implements ForumService{
    @Autowired
    ForumRepo forumRepo;
    @Autowired
    CategoryDto categoryDto;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ForumDto forumDto;
    @Autowired
    ForumEntity forumEntity;
    
    @Autowired
    ForumRepo forumrepo;
    
    @Autowired
	UserServiceDelegate userServiceDelegate;
    
	int lastQuestionid=0;
	//String question=forumDto.getQuestion();
	@Override
	public ForumDto postNewQuestion(ForumDto forumDto, String authToken) {
		lastQuestionid = lastQuestionid+1;
		forumDto.setQuestionId(lastQuestionid);
		forumEntity = mapper.map(forumDto,ForumEntity.class);
		forumEntity = forumRepo.save(forumEntity);
		forumDto = mapper.map(forumEntity,ForumDto.class);
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
		
		//throw new InvalidqusIdException (""+questionId);
		
		
	}

	
	@Override
	public ForumDto updateQuestion(long questionId, ForumDto forum, String auth) {
		
		if(!userServiceDelegate.isLoggedInUser(auth)) {
			
			throw new InvalidAuthorizationTokenException(auth);
		}
		
		ForumEntity forumEntity = forumRepo.getById(questionId);

		if (forumEntity!=null){

			forumEntity.setQuestion(forum.getQuestion());

	        ForumEntity updatedquestion = forumRepo.save(forumEntity);

	        return new ForumDto(updatedquestion.getQuestionid(), updatedquestion.getQuestion());
	    }
	    
		throw new InvalidCategoryIdException(""+questionId);
	}
	}


