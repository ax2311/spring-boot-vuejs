package com.springBlog.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.entities.PropertyManagement;
import com.springBlog.entities.Tender;
import com.springBlog.entities.TenderDetails;
import com.springBlog.pojo.SearchCurrentTender;
import com.springBlog.entities.Supplier;
import com.springBlog.repositories.EngineerVendorRepository;
import com.springBlog.repositories.PropertyManagementRepository;
import com.springBlog.repositories.SupplierRepository;
import com.springBlog.repositories.TenderDetailsRepository;
import com.springBlog.repositories.TenderRepository;
import com.springBlog.repositories.UserRepository;

@Service
public class PropertyManagementService {

	@Autowired
	private PropertyManagementRepository propertyManagementRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EngineerVendorRepository engineerVendorRepository;
	
	@Autowired
	private TenderDetailsRepository tenderDetailsRepository;
	
	@Autowired
	private TenderRepository tenderRepository;
	
	public void saveTender(String username, String[] ids, String companyType, String title, String description, Date fromDateTime, Date toDateTime, String address, 
			int maximumPrice, int minimumPrice, byte[] document){
		PropertyManagement pm = userRepository.findByUsername(username).getPropertyManagement();
		Tender tender = new Tender();
		tender.setPropertyManagement(pm);
		tender.setDateAdded(new Date());
		tender.setDocument(document);
		tender.setCompanyType(companyType);
		tender.setAddress(address);
		tender.setTitle(title);
		tender.setDescription(description);
		tender.setFromDate(fromDateTime);
		tender.setToDate(toDateTime);
		tender.setMaximumPrice(maximumPrice);
		tender.setMinimumPrice(minimumPrice);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 60);
		tender.setDateExpired(calendar.getTime());
		
		for(String id: ids){
			TenderDetails tenderDetail = new TenderDetails();
			tenderDetail.setCompanyType(companyType);
			if ("S".equalsIgnoreCase(companyType))
				tenderDetail.setSupplier(supplierRepository.findById(Long.valueOf(id)).get());
			else if ("E".equalsIgnoreCase(companyType))
				tenderDetail.setEngineerVendor(engineerVendorRepository.findById(Long.valueOf(id)).get());
			
			tenderDetail.setTender(tender);
			tenderDetailsRepository.save(tenderDetail);
		}		
	}
	
	public List<SearchCurrentTender> getCurrentTenders(String username){
		return tenderRepository.findByPmUsername(username);
	}
}
