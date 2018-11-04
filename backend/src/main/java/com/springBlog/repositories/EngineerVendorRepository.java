package com.springBlog.repositories;

import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.Category;
import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.User;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchVendorResult;

@Repository
public interface EngineerVendorRepository extends JpaRepository<EngineerVendor,Long>,  EngineerVendorRepositoryCustom{

	public static final String CATEGORY = "CATEGORY";	
	public static final String USERNAME = "USERNAME";	
	public static final String COMPANY = "COMPANY";
	
	List<SearchEngineerVendor> findEngineerVendorsByCategoryId( Map<String,String> searchValue);
}
