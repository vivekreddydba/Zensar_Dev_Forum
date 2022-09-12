package com.zensar.df.service;

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
	UserServiceDelegate userServiceDelegate;
    
	//String question=forumDto.getQuestion();
	@Override
	public ForumDto postNewQuestion(ForumDto forumDto, String authToken) {
		if (!userServiceDelegate.isLoggedInUser(authToken)) {

			throw new InvalidAuthorizationTokenException(authToken);
		}
		
		if (!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(authToken)))) {

			throw new InvalidRoleException("" + "User Not Allowed");
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
		
		ForumEntity forumEntity = forumRepo.getById(questionId);

		if(forumRepo.existsById(questionId)){


			forumEntity.setQuestion(forum.getQuestion());

	        ForumEntity updatedquestion = forumRepo.save(forumEntity);

	        return new ForumDto(updatedquestion.getQuestionid(), updatedquestion.getQuestion());
	    }
	    
		throw new InvalidQuestionIdException(""+questionId);
	}
	
	@Override
	public List<ForumDto> getAllQuestionsById(long categoryid) {
		if(forumRepo.existsBycategory_id(categoryid)) {
		List<ForumEntity> forumEntities = forumRepo.findBycategory_id(categoryid);

			return forumEntities.stream().map(i -> mapper.map(i, ForumDto.class)).collect(Collectors.toList());
		}
		throw new InvalidCategoryIdException("Catgory Id is not found:"+categoryid);
	}
	}




