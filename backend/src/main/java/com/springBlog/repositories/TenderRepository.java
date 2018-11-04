package com.springBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBlog.entities.Supplier;
import com.springBlog.entities.Tender;

public interface TenderRepository extends JpaRepository<Tender,Long>, TenderRepositoryCustom{

}
