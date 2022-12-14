package com.zensar.df.service;

import java.util.List;
import com.zensar.df.entity.ForumEntity;
import org.springframework.http.ResponseEntity;
import com.zensar.df.dto.ForumDto;

public interface ForumService {

	ForumDto postNewQuestion(ForumDto forumDto, String authToken);
	boolean deleteQuestionbyId(long questionId,String authToken);
    ForumDto updateQuestion(long questionId, ForumDto question, String auth);
    public List<ForumDto> getAllQuestionsByCategoryId(long categoryid);
    List<ForumDto> getAllQuestionsByUser(String authToken);
    List<ForumDto> findByText(String search);
	
}
