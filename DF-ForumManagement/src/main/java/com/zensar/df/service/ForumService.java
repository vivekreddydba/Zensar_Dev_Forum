package com.zensar.df.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zensar.df.dto.ForumDto;

public interface ForumService {

	ForumDto postNewQuestion(ForumDto forumDto, String authToken);
	boolean deleteQuestionbyId(long questionId,String authToken);
    ForumDto updateQuestion(long questionId, ForumDto question, String auth);
    public List<ForumDto> getAllQuestionsById(long categoryid);
	
}
