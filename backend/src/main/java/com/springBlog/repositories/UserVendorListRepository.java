package com.springBlog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.Supplier;
import com.springBlog.entities.UserVendorList;
import com.springBlog.pojo.SearchEngineerVendor;

@Repository
public interface UserVendorListRepository extends JpaRepository<UserVendorList,Long>, UserVendorListRepositoryCustom{

	UserVendorList save (UserVendorList userVendorList);
	
	List<SearchEngineerVendor> findEngineerVendorsByUsernameByCategoryId(String username, long catId);
	
	List<UserVendorList> findByPropertyManagementIdAndEngineerVendor (Long propertyManagementId, EngineerVendor engineerVendor);
	
	List<UserVendorList> findByPropertyManagementIdAndSupplier (Long propertyManagementId, Supplier supplier);
	
	void delete(UserVendorList userVendorList);
}
