package com.springBlog.repositories;

import java.util.List;
import java.util.Map;
import com.springBlog.pojo.SearchEngineerVendor;

public interface EngineerVendorRepositoryCustom {

	List<SearchEngineerVendor> findEngineerVendorsByCategoryId( Map<String,String> searchValue);

}
