package com.springBlog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.Supplier;
import com.springBlog.entities.UserVendorList;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;
import com.springBlog.repositories.PropertyManagementRepository;
import com.springBlog.repositories.SupplierRepository;
import com.springBlog.repositories.UserVendorListRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private PropertyManagementRepository propertyManagementRepository;
	
	@Autowired
	private UserVendorListRepository userVendorListRepository;
	
	public List<SearchSupplier> getSupplier(Map<String,String> searchValue){
		List<SearchSupplier> list = new ArrayList<SearchSupplier>();
		list = supplierRepository.findSuppliersByCategoryId(searchValue);
		return list;
	}
	
	public List<SearchSupplier> getSuppliersFromUserVendorList(String username, Long catId){
		List<SearchSupplier> list = new ArrayList<SearchSupplier>();
		list = userVendorListRepository.findSuppliersByUsernameByCategoryId(username, catId);
		return list;
	}
	
	public List [] addSuppliersToUserVendorList(String username, String[] supplierIds){
		long pmId = propertyManagementRepository.findPropertyManagementIdByUsername(username);
		List<Long> supplierIdsLong = Arrays.asList(supplierIds).stream().map(Long::parseLong).collect(Collectors.toList());
		List<Supplier> suppliers = supplierRepository.findAllById(supplierIdsLong);
		List successList = new ArrayList<String>();
		List failList = new ArrayList<String>();
		List [] result = new List [2];
		result[0] = successList;
		result[1] = failList;
		
		for (Supplier s : suppliers){
			try{
				UserVendorList userVendorList = new UserVendorList();
				userVendorList.setPropertyManagementId(pmId);
				userVendorList.setSupplier(s);
				userVendorList.setCompanyType("S");
				
				userVendorList = userVendorListRepository.save(userVendorList);
				} catch (Throwable ex) {
					failList.add(s.getCompanyName());
					continue;
				}
				successList.add(s.getCompanyName());
		}
		
		return result;
	}
	
	public List [] deleteSuppliersfromUserVendorList(String username, String[] supplierIds){
		long pmId = propertyManagementRepository.findPropertyManagementIdByUsername(username);
		List<Long> supplierIdsLong = Arrays.asList(supplierIds).stream().map(Long::parseLong).collect(Collectors.toList());
		List<Supplier> suppliers = supplierRepository.findAllById(supplierIdsLong);
		List successList = new ArrayList<String>();
		List failList = new ArrayList<String>();
		List [] result = new List [2];
		result[0] = successList;
		result[1] = failList;
		
		for (Supplier s : suppliers){
			try{
				List<UserVendorList> userVendorList = userVendorListRepository.findByPropertyManagementIdAndSupplier(pmId, s);
				
				userVendorListRepository.delete(userVendorList.get(0));
				} catch (Throwable ex) {
					failList.add(s.getCompanyName());
					continue;
				}
				successList.add(s.getCompanyName());
		}
		
		return result;
	}
}
