package com.springBlog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.entities.EngineerVendor;

import com.springBlog.entities.UserVendorList;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchVendorResult;
import com.springBlog.repositories.EngineerVendorRepository;
import com.springBlog.repositories.PropertyManagementRepository;
import com.springBlog.repositories.UserRepository;
import com.springBlog.repositories.UserVendorListRepository;

@Service
public class EngineerVendorService {

	@Autowired
	private EngineerVendorRepository engineerVendorRepository;
	
	@Autowired
	private UserVendorListRepository userVendorListRepository;
	
	@Autowired
	private PropertyManagementRepository propertyManagementRepository;
	
	public List<SearchEngineerVendor> getEngineerVendor(Map<String,String> searchValue){
		List<SearchEngineerVendor> list = new ArrayList<SearchEngineerVendor>();
		list = engineerVendorRepository.findEngineerVendorsByCategoryId(searchValue);
		return list;
	}
	
	public List<SearchEngineerVendor> getVendorsFromUserVendorList(String username, Long catId){
		List<SearchEngineerVendor> list = new ArrayList<SearchEngineerVendor>();
		list = userVendorListRepository.findEngineerVendorsByUsernameByCategoryId(username, catId);
		return list;
	}
	
	public List [] addVendorsToUserVendorList(String username, String[] vendorIds){
		long pmId = propertyManagementRepository.findPropertyManagementIdByUsername(username);
		List<Long> vendorIdsLong = Arrays.asList(vendorIds).stream().map(Long::parseLong).collect(Collectors.toList());
		List<EngineerVendor> engineerVendors = engineerVendorRepository.findAllById(vendorIdsLong);
		List successList = new ArrayList<String>();
		List failList = new ArrayList<String>();
		List [] result = new List [2];
		result[0] = successList;
		result[1] = failList;
		
		for (EngineerVendor ev : engineerVendors){
			try{
				UserVendorList userVendorList = new UserVendorList();
				userVendorList.setPropertyManagementId(pmId);
				userVendorList.setEngineerVendor(ev);
				userVendorList.setCompanyType("E");
				
				userVendorList = userVendorListRepository.save(userVendorList);
				} catch (Throwable ex) {
					failList.add(ev.getCompanyName());
					continue;
				}
				successList.add(ev.getCompanyName());
		}
		
		return result;
	}
	
	public List [] deleteVendorsfromUserVendorList(String username, String[] vendorIds){
		long pmId = propertyManagementRepository.findPropertyManagementIdByUsername(username);
		List<Long> vendorIdsLong = Arrays.asList(vendorIds).stream().map(Long::parseLong).collect(Collectors.toList());
		List<EngineerVendor> engineerVendors = engineerVendorRepository.findAllById(vendorIdsLong);
		List successList = new ArrayList<String>();
		List failList = new ArrayList<String>();
		List [] result = new List [2];
		result[0] = successList;
		result[1] = failList;
		
		for (EngineerVendor ev : engineerVendors){
			try{
				List<UserVendorList> userVendorList = userVendorListRepository.findByPropertyManagementIdAndEngineerVendor(pmId, ev);
				
				userVendorListRepository.delete(userVendorList.get(0));
				} catch (Throwable ex) {
					failList.add(ev.getCompanyName());
					continue;
				}
				successList.add(ev.getCompanyName());
		}
		
		return result;
	}
}
