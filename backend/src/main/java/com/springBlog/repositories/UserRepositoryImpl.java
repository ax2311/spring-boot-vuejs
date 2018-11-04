package com.springBlog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.springBlog.entities.User;
import com.springBlog.entities.UserRoles;
import com.springBlog.entities.Role;

public class UserRepositoryImpl implements UserRepositoryCustom{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public User create(User user, List<Role> roleList) {
        em.persist(user);
        for (Role role: roleList){
        	UserRoles userRole = new UserRoles();        	
        	userRole.setRole(role);
        	userRole.setUserId(user.getId());
        	user.getUserRoles().add(userRole);
        	em.persist(userRole);
    	}
        em.persist(user);
        return user;
    }

}
