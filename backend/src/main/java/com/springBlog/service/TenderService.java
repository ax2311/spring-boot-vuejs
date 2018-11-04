package com.springBlog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.repositories.SupplierRepository;
import com.springBlog.repositories.TenderDetailsRepository;
import com.springBlog.repositories.UserRepository;
import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.Supplier;
import com.springBlog.entities.TenderDetails;
import com.springBlog.entities.User;
import com.springBlog.pojo.SearchReceivedTender;
import com.springBlog.pojo.SearchTenderDetail;
import com.springBlog.repositories.EngineerVendorRepository;
import com.springBlog.repositories.TenderRepository;

@Service
public class TenderService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TenderRepository tenderRepository;
	
	@Autowired
	private TenderDetailsRepository tenderDetailsRepository;
	
	public String getTenderDetailRemark(Long tenderDetailId) {
		return tenderDetailsRepository.findById(tenderDetailId).get().getRemarks();
	}
	
	public List<SearchReceivedTender> getTenders(String username) {
		Long id;
		Supplier supplier= userRepository.findByUsername(username).getSupplier();
		EngineerVendor engineerVendor = userRepository.findByUsername(username).getEngineerVendor();
		String type;
		if(supplier != null){
			type = "S";
			id = supplier.getSupplierId();			
		}
		else  if (engineerVendor != null){
			type = "E";
			id = engineerVendor.getEngineerVendorId();
		}
		else {
			return null;
		}
		return tenderRepository.findByIdAndType(id, type);
		
	}
	
	public List<SearchTenderDetail> getTenderDetailsByUsernameAndTenderId(String username, Long tenderId, String companyType){
		return tenderRepository.findDetailsyUsernameAndTenderId(username, tenderId, companyType);
	}
	
	public Map<String, String> getTenderDescriptionAndBidded(long tenderDetailId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("description", tenderDetailsRepository.findById(tenderDetailId).get().getTender().getDescription());
		map.put("bidded", tenderDetailsRepository.findById(tenderDetailId).get().getBidded());
		return map;
	}
	
	public String getTenderDescriptionByTenderId(long tenderId){
		return tenderRepository.findById(tenderId).get().getDescription();
	}
	
	public void bidTender(String username, Long tenderDetailId, String remarks, int bidPrice) {
		TenderDetails tenderDetails = tenderDetailsRepository.findById(tenderDetailId).get();
		User user = userRepository.findByUsername(username);
		Long id;
		id = (user.getEngineerVendor() != null) ? user.getEngineerVendor().getEngineerVendorId() : user.getSupplier().getSupplierId();
		if ("S".equalsIgnoreCase(tenderDetails.getCompanyType())){
			if (tenderDetails.getSupplier().getSupplierId() != id)
				throw new RuntimeException("User and tender receiver not match");
		} else {
			if (tenderDetails.getEngineerVendor().getEngineerVendorId() != id)
				throw new RuntimeException("User and tender receiver not match");
		}
		tenderDetails.setBidded("Y");
		tenderDetails.setRemarks(remarks);
		tenderDetails.setPrice(bidPrice);
		tenderDetailsRepository.save(tenderDetails);
	}
	
	public boolean acceptBid(String username, long tenderDetailId){
		TenderDetails tenderDetails = tenderDetailsRepository.findById(tenderDetailId).get();
		User user = userRepository.findByUsername(username);
		if (tenderDetails.getTender().getPropertyManagement().getPropertyManagementId() != user.getPropertyManagement().getPropertyManagementId())
			throw new RuntimeException("User and tender holder not match");
		
		tenderDetails.setAccepted("Y");
		tenderDetailsRepository.save(tenderDetails);
		
		return true;
	}
}
