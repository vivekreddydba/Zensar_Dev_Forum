package com.zensar.df.service;

import com.zensar.df.dto.ForumDto;

public interface ForumService {

	ForumDto postNewQuestion(ForumDto forumDto, String authToken);
	
}
