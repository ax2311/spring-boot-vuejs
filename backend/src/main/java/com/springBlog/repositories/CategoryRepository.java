package com.springBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	Category findCategoryByCategoryId(Long categoryId);
}
