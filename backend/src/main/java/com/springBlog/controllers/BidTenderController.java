package com.springBlog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.pojo.SearchReceivedTender;
import com.springBlog.service.TenderService;

@RestController
public class BidTenderController {

	@Autowired
	TenderService tenderService;
	
	@GetMapping(value="receivedTenders")
	public List<SearchReceivedTender> getReceivedTenders(){
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return tenderService.getTenders(customUserDetails.getUsername());
	}
	
	@GetMapping(value="getTenderDescriptionAndBidded")
	public Map<String, String>  getTenderDescriptionAndBidded(@RequestParam("tenderDetailId") long tenderDetailId){
		return tenderService.getTenderDescriptionAndBidded(tenderDetailId);
	}
	
	@PostMapping(value="bidTender")
	public void bidTender(@RequestParam("tenderDetailId") long tenderDetailId, @RequestParam("remarks") String remarks,
			@RequestParam("bidPrice") int bidPrice) {
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		tenderService.bidTender(customUserDetails.getUsername(),tenderDetailId,remarks,bidPrice);
	}
}
