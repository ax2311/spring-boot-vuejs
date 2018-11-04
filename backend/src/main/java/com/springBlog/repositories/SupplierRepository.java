package com.springBlog.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBlog.entities.Category;
import com.springBlog.entities.Supplier;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;

public interface SupplierRepository extends JpaRepository<Supplier,Long>,  SupplierRepositoryCustom{

	public static final String CATEGORY = "CATEGORY";	
	public static final String USERNAME = "USERNAME";	
	public static final String COMPANY = "COMPANY";
	
	List<SearchSupplier> findSuppliersByCategoryId( Map<String,String> searchValue);
}
