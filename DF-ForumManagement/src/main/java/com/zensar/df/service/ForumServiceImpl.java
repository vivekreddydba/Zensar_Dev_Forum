package com.zensar.df.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.CategoryEntity;
import com.zensar.df.entity.ForumEntity;
import com.zensar.df.exception.InvalidCategoryIdException;
import com.zensar.df.exception.InvalidQuestionIdException;
import com.zensar.df.repo.CategoryRepo;
import com.zensar.df.repo.ForumRepo;
import com.zensar.df.utils.JwtUtils;

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
    JwtUtils jwtutils;
    @Autowired
    ForumRepo forumrepo;
    @Autowired
    CategoryEntity categoryEntity;
    
    
    @Autowired
	UserServiceDelegate userServiceDelegate;
    
	//String question=forumDto.getQuestion();
	@Override
	public ForumDto postNewQuestion(ForumDto forumDto, String authToken) {
		forumEntity = mapper.map(forumDto,ForumEntity.class);
		CategoryEntity categoryEntity = categoryRepo.getById(forumDto.getCategoryid());
		forumEntity.setCategory(categoryEntity);
		forumEntity.setStatus(true);
		String authToken1 = authToken.substring(7, authToken.length());
		String username = jwtutils.extractUsername(authToken1);
		forumEntity.setUsername(username);
		forumEntity = forumRepo.save(forumEntity);
		forumDto = mapper.map(forumEntity,ForumDto.class);
		forumDto.setCategoryid(forumEntity.getCategory().getId());
		return forumDto;
		//return new ForumDto(forumDto.getQuestionId(),forumDto.getQuestion(),true,forumDto.getAnswers());
	}
	
	@Override
	public boolean deleteQuestionbyId(long questionId,String auth) {
		
		if(forumRepo.existsById(questionId)) {
			forumRepo.deleteById(questionId);
		    return true;
		}
		return false;
				
		
	}

	
	@Override
    public ForumDto updateQuestion(long questionId, ForumDto forum, String auth) {
        
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
	public void setForumRepo(ForumRepo forumRepo) {
		this.forumRepo = forumRepo;
	}
	
	public void setForumRepo(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	@Override
	public List<ForumDto> getAllQuestionsByUser(String authToken) {
		String authToken1 = authToken.substring(7, authToken.length());
		String username = jwtutils.extractUsername(authToken1);
		List<ForumEntity> forumEntityUsers = forumRepo.findByusername(username);
		List<ForumDto> forumDtoList = new ArrayList<>();
		for(ForumEntity forumEntity: forumEntityUsers) {
			forumEntity.setStatus(true);
			ForumDto forum = 
					new ForumDto(forumEntity.getQuestionid(),forumEntity.getQuestion(),forumEntity.isStatus(),forumEntity.getAnswers(),forumEntity.getCategory().getId(),forumEntity.getUsername());
			forumDtoList.add(forum);
		}
		return forumDtoList;
	}
	
	@Override
    public List<ForumDto> findByText(String search) {
        
        List<ForumEntity> entity = forumRepo.findByText(search);
        List<ForumDto> forumDtoList = new ArrayList<>();
        for(ForumEntity forumEntity:entity) {
        	ForumDto forum = new ForumDto(forumEntity.getQuestionid(),forumEntity.getQuestion(),forumEntity.getStatus(),forumEntity.getAnswers(),forumEntity.getCategory().getId());
        	forumDtoList.add(forum);
        }
        return forumDtoList;
    }

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}
	public void setCategoryRepo(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
}



