package com.springBlog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	Role findByName(String name);
	
	@Query("SELECT r FROM User u join u.userRoles ur join ur.role r where u.username = :username") 
	List<Role> findRoleByUsername(@Param("username") String username);
}
