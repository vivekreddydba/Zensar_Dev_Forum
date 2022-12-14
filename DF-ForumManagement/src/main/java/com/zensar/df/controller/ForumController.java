package com.zensar.df.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.zensar.df.dto.CategoryDto;
import com.zensar.df.dto.ForumDto;
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidCategoryIdException;
//import com.zensar.df.service.CategoryService;
import com.zensar.df.service.ForumService;
import com.zensar.df.service.UserServiceDelegate;
import com.zensar.df.utils.JwtUtils;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/devforum")
public class ForumController {
	@Autowired
	private ForumService forumService;
	@Autowired
	UserServiceDelegate userServiceDelegate;
	@Autowired
    JwtUtils jwtutils;
//	@Autowired
	//private CategoryDto categoryDto;
    @Autowired
    ForumDto forumDto;
	@PostMapping(value="/question", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="New question", description="This request creates new question which is posted by user")
	public ResponseEntity<ForumDto> postNewQuestion(@RequestBody ForumDto forumDto, @RequestHeader("Authorization") String authToken) throws IOException {
		if (!userServiceDelegate.isLoggedInUser(authToken)) {

			throw new InvalidAuthorizationTokenException(authToken);
		}
		forumDto = this.forumService.postNewQuestion(forumDto,authToken);
		if(forumDto.getQuestion()==null || forumDto.getQuestion().isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(forumDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/question/{id}")
	@Operation(summary="New question", description="This request deletes question by question id")
	public ResponseEntity<Boolean> deletequestionbyid(@PathVariable("id") long questionId,@RequestHeader("Authorization")  String authToken){
		 if(!userServiceDelegate.isLoggedInUser(authToken)) {
				
				throw new InvalidAuthorizationTokenException(authToken);
	       }
		if(forumService.deleteQuestionbyId(questionId,authToken))
		{
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value="/question/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Update question", description="This request updates specified forum with name passed and present in database")
	public ResponseEntity<ForumDto> updateQuestion(@RequestHeader("Authorization") String authToken, @PathVariable("id") Long id, @RequestBody ForumDto question){
        if(!userServiceDelegate.isLoggedInUser(authToken)) {
            
            throw new InvalidAuthorizationTokenException(authToken);
        }
		if(question.getQuestion()!=null) {
		   return new ResponseEntity<ForumDto>(forumService.updateQuestion(id,  question, authToken), HttpStatus.OK);
		}
		return new ResponseEntity<ForumDto>(HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping(value="/question/search/category/{id}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Get All Questions by ID", description="This request returns all questions with id passed and present in database")
	public List<ForumDto> getAllQuestionsById(@Parameter(description="Categoryid",required=true)  @PathVariable("id") Long id){
		
		if(id <=0 ) {
			throw new InvalidCategoryIdException();
		}
		return forumService.getAllQuestionsByCategoryId(id);
}
	@GetMapping(value="/question",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Get All Questions by logged in user", description="This request returns all questions posted by present logged in user")
	public ResponseEntity<List<ForumDto>> getAllQuestionsByUser(@RequestHeader("Authorization") String authToken){
        if(!userServiceDelegate.isLoggedInUser(authToken)) {         
            throw new InvalidAuthorizationTokenException(authToken);
        }
		return new ResponseEntity<>(forumService.getAllQuestionsByUser(authToken),HttpStatus.OK);
}
	
			@Operation(summary="Search the questions using devops as the key",description="This API endpoint is used for Searching")
		    @GetMapping(value="/question/search/{searchText}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
		    public ResponseEntity<List<ForumDto>> findByText(@PathVariable("searchText")String search) {        
				List<ForumDto> searchItem=forumService.findByText(search);
		        return new ResponseEntity<>(searchItem,HttpStatus.OK);
			}
    
	
}
