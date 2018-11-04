package com.springBlog.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.entities.Tender;
import com.springBlog.pojo.UserRegistration;
import com.springBlog.service.PropertyManagementService;

@RestController
public class PublishTenderController {
	
	@Autowired
	private PropertyManagementService propertyManagementService;
	
	@PostMapping(value = {"/postSupplierTender/{ids}", "/postVendorTender/{ids}"})
	public String postVendorTender(@PathVariable String[] ids, @RequestParam("companyType") String companyType,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("address") String address, @RequestParam("maximumPrice") String maximumPrice,
			@RequestParam("minimumPrice") String minimumPrice, @RequestParam("document") byte[] document,
			@RequestParam("fromTime") String fromTime, @RequestParam("toTime") String toTime) throws ParseException
	{
		Date fromDateTime = new Date();
		Date toDateTime = new Date();
		
		if (!"S".equalsIgnoreCase(companyType) && !"E".equalsIgnoreCase(companyType))
			return "Please select vendor or supplier";
		
		if(title != null && title.length() > 200)
			return "Title is too long";
		
		if(description != null && description.length() > 1000)
			return "Description is too long";	
		
		if (document != null && document.length > 65535)
			return "Document should be < 5 MB";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (fromDate != null && !fromDate.isEmpty())
			fromDateTime = formatter.parse(fromDate + " " + fromTime);
		if (toDate != null && !toDate.isEmpty())
			toDateTime = formatter.parse(toDate + " " +toTime);
		
		int maximumPriceInt = "".equals(maximumPrice) ? 0 : Integer.valueOf(maximumPrice);
		int minimumPriceInt = "".equals(minimumPrice) ? 0 : Integer.valueOf(minimumPrice);
		
		CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		propertyManagementService.saveTender(customUserDetails.getUsername(), ids, companyType, title, description, fromDateTime, toDateTime, address, maximumPriceInt, minimumPriceInt,
				document);
		
		return "success";
	}
}
