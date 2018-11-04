package com.springBlog.repositories;

import org.springframework.stereotype.Repository;
import com.springBlog.entities.CompanyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompanyCategoryRepository extends JpaRepository<CompanyCategory, Long> {

}
