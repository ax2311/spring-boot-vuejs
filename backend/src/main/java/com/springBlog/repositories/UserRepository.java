package com.springBlog.repositories;

import com.springBlog.entities.Role;
import com.springBlog.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom {
    User findByUsername(String username);
    
}