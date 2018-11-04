package com.springBlog.repositories;

import java.util.List;
import java.util.Map;

import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;

public interface SupplierRepositoryCustom {

	List<SearchSupplier> findSuppliersByCategoryId( Map<String,String> searchValue);
}
