package com.springBlog.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

public class PropertyManagementRepositoryImpl implements PropertyManagementRepositoryCustom{
	 @PersistenceContext
	 private EntityManager em;
	    
	@Override
	@Transactional
	public long findPropertyManagementIdByUsername(String username){
		String jpql = " select pm.propertyManagementId from User u join u.propertyManagement pm " + 
					" where u.username = :name ";
		
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		
		query.setParameter("name", username);
		
		return query.getSingleResult();
	}
}
