package com.springBlog.repositories;

import java.util.List;

import com.springBlog.pojo.SearchCurrentTender;
import com.springBlog.pojo.SearchReceivedTender;
import com.springBlog.pojo.SearchTenderDetail;

public interface TenderRepositoryCustom {

	List<SearchReceivedTender> findByIdAndType(long id, String type);
	
	List<SearchCurrentTender> findByPmUsername(String username);
	
	List<SearchTenderDetail> findDetailsyUsernameAndTenderId(String username, Long tenderId, String companyType);
}
