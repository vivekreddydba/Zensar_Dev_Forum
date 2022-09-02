package com.zensar.df.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.df.dto.CategoryDto;
import com.zensar.df.service.CategoryService;
@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/devforum")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@PostMapping(value="/category", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> createNewCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDTO = this.categoryService.createNewCategory(categoryDto);
		if(categoryDto.getName()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoryDto>(categoryDTO, HttpStatus.CREATED);
	} 
	
	@GetMapping(value="/category",produces= MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDto> GetAllCategories(){
		
		return categoryService.GetAllCategories();
		
	}
	
}

