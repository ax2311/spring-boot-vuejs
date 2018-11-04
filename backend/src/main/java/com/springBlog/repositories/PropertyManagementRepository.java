package com.springBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.Category;
import com.springBlog.entities.PropertyManagement;

@Repository
public interface PropertyManagementRepository extends JpaRepository<PropertyManagement,Long>{
	long findPropertyManagementIdByUsername(String username);

}
