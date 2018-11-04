package com.springBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.entities.Category;
import com.springBlog.repositories.CategoryRepository;
import com.springBlog.repositories.CompanyCategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CompanyCategoryRepository companyCategoryRepository;
    
    public List<Category> getAllCategories(){
    	return categoryRepository.findAll();
    }
    
    public Category getCategoryByCategoryId(String categoryId){    	
    	if(categoryId == null || categoryId.isEmpty()) return null;
    	return categoryRepository.findCategoryByCategoryId(Long.valueOf(categoryId));
    }
}
