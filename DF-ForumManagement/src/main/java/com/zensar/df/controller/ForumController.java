package com.zensar.df.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
//import com.zensar.df.service.CategoryService;
import com.zensar.df.service.ForumService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/devforum")
public class ForumController {
	@Autowired
	private ForumService forumService;
//	@Autowired
	//private CategoryDto categoryDto;
    @Autowired
    ForumDto forumDto;
	@PostMapping(value="/question", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="New question", notes="This request creates new question which is posted by user")
	public ResponseEntity<ForumDto> postNewQuestion(@RequestBody ForumDto forumDto, @RequestHeader(value="Authorization", required=false) String authToken) throws IOException {
		forumDto = this.forumService.postNewQuestion(forumDto,authToken);
		if(forumDto.getQuestion()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(forumDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/question/{id}")
	@ApiOperation(value="New question", notes="This request deletes question by question id")
	public ResponseEntity<Boolean> deletequestionbyid(@PathVariable("id") long questionId,@RequestHeader("Authorization")  String authToken){
		if(forumService.deleteQuestionbyId(questionId, authToken))
		{
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
	}
	
	
}
