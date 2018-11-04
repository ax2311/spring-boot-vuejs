package com.springBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBlog.entities.Supplier;
import com.springBlog.entities.TenderDetails;

public interface TenderDetailsRepository  extends JpaRepository<TenderDetails,Long> {

}
