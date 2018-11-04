package com.springBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.Category;
import com.springBlog.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

	District findByDistrictId(Long districtId);
}
