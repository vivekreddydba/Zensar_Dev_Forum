package com.zensar.df.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

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

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.exception.InvalidAuthorizationTokenException;
import com.zensar.df.exception.InvalidRoleException;
import com.zensar.df.service.CategoryService;
import com.zensar.df.service.UserServiceDelegate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/devforum")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	UserServiceDelegate userServiceDelegate;
	@PostMapping(value="/category", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="New question category", description="This request creates new question category")
	public ResponseEntity<CategoryDto> createNewCategory(@Valid @RequestBody CategoryDto categoryDto, @RequestHeader("Authorization") String authToken) throws IOException {
		if (!userServiceDelegate.isLoggedInUser(authToken)) {

			throw new InvalidAuthorizationTokenException(authToken);
		}
		
		if (!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(authToken)))) {

			throw new InvalidRoleException("" + "User Not Allowed");
		}
		CategoryDto categoryDTO = this.categoryService.createNewCategory(categoryDto,authToken);
		return new ResponseEntity<CategoryDto>(categoryDTO, HttpStatus.CREATED);
	} 
	
	@GetMapping(value="/category",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Gets all categories", description="This request returns all categories which are created in database")
	public List<CategoryDto> GetAllCategories(){
		
		return categoryService.GetAllCategories();
		
	}
	
	@GetMapping(value="/category/{id}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Gets category by ID", description="This request returns specified category with id passed and present in database")
	public CategoryDto getAllCategoriesById(@Parameter(description="Category ID",required=true)  @PathVariable("id") Long id){
		
		
		
		return categoryService.getAllCategoriesById(id);
	}
	
	@PutMapping(value="/category/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary="Updates category by ID", description="This request updates specified category with name passed and present in database")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestHeader("Authorization") String authToken, @PathVariable("id") Long id, @RequestBody CategoryDto category){
if(!userServiceDelegate.isLoggedInUser(authToken)) {
			
			throw new InvalidAuthorizationTokenException(authToken);
		}
		
		if(!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(authToken)))) {
			
			throw new InvalidRoleException("");
		}
		if(!"".equals(category.getName())) {
		   return new ResponseEntity<CategoryDto>(categoryService.updateCategory(id, category, authToken), HttpStatus.OK);
		}
		return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
    }
	@DeleteMapping(value="/category/{id}")
    @Operation(summary="Deletes category by ID", description= "This request deletes specifies category with mentioned Id BY THE USER that is present in the database")
    public ResponseEntity<Boolean> DeleteCategoryById(@RequestHeader("Authorization") String authToken,@PathVariable("id") long id ){
		if (!userServiceDelegate.isLoggedInUser(authToken)) {
	           throw new InvalidAuthorizationTokenException(authToken);
	        }
	        
	        if (!("ROLE_ADMIN".equals(userServiceDelegate.isAdminRole(authToken)))) {
	           throw new InvalidRoleException(""+"User Not Allowed");
	        }
		if(categoryService.DeleteCategoryById(id, authToken))
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }
}


