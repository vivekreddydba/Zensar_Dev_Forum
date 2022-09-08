package com.zensar.df.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.ForumEntity;
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

}
