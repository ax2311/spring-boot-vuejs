package com.springBlog.repositories;

import java.util.List;

import com.springBlog.entities.UserVendorList;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;

public interface UserVendorListRepositoryCustom {
	UserVendorList save (UserVendorList userVendorList);
	
	List<SearchEngineerVendor> findEngineerVendorsByUsernameByCategoryId(String username, long catId);
	
	List<SearchSupplier> findSuppliersByUsernameByCategoryId(String username, long catId);
}
