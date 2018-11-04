package com.springBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.pojo.SearchCurrentTender;
import com.springBlog.pojo.SearchTenderDetail;
import com.springBlog.service.PropertyManagementService;
import com.springBlog.service.TenderService;

@RestController
public class CurrentTenderController {
	
	@Autowired
	PropertyManagementService propertyManagementService;
	
	@Autowired
	TenderService tenderService;

	@GetMapping(value="currentTenders")
	public List<SearchCurrentTender> currentTenders(){
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return propertyManagementService.getCurrentTenders(customUserDetails.getUsername());
	}
	
	@GetMapping(value="getRemarks")
	public String getRemarks(@RequestParam("tenderDetailId") Long tenderDetailId){
		return tenderService.getTenderDetailRemark(tenderDetailId);
	}
	
	@GetMapping(value="getTenderDescriptionByTenderId")
	public String getTenderDescription(@RequestParam("tenderId") long tenderId){
		return tenderService.getTenderDescriptionByTenderId(tenderId);
	}
	
	@GetMapping(value="getTenderDetails")
	public List<SearchTenderDetail> getTenderDetails(@RequestParam("tenderId") long tenderId,@RequestParam("companyType") String companyType){
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return tenderService.getTenderDetailsByUsernameAndTenderId(customUserDetails.getUsername(), tenderId, companyType);
	}
	
	@PutMapping(value="acceptBid")
	public boolean acceptBid(@RequestParam("tenderDetailId") long tenderDetailId){
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return tenderService.acceptBid(customUserDetails.getUsername(), tenderDetailId);
	}
}
