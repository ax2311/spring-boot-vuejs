package com.springBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.entities.Category;
import com.springBlog.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/categories")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
}
