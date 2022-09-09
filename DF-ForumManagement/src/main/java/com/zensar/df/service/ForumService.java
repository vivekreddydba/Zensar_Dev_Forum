package com.zensar.df.service;

import org.springframework.http.ResponseEntity;

import com.zensar.df.dto.ForumDto;

public interface ForumService {

	ForumDto postNewQuestion(ForumDto forumDto, String authToken);

	 boolean deleteQuestionbyId(long questionId,String authToken);
	// public ForumDto postNewQuestion(ForumDto forumDto, String authToken);
		ForumDto updateQuestion(long questionId, ForumDto question, String auth);

	//Boolean deleteQuestionbyId(Long questionId);
	
}
