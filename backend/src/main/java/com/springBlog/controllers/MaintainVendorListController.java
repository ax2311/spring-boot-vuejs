package com.springBlog.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.Utils;
import com.springBlog.config.CustomUserDetails;
import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.User;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;
import com.springBlog.pojo.SearchVendorResult;
import com.springBlog.repositories.EngineerVendorRepository;
import com.springBlog.repositories.SupplierRepository;
import com.springBlog.service.EngineerVendorService;
import com.springBlog.service.SupplierService;


@RestController
public class MaintainVendorListController {

	@Autowired
	EngineerVendorService engineerVendorService;
	
	@Autowired
	SupplierService supplierService;
	
    @GetMapping(value = "/getVendorsByCategory")
    public List<SearchEngineerVendor> getVendorsByCategoryIdAndUsernameAndCompanyName(@RequestParam("catId")  Long catId,
    		@RequestParam("username")  String username,
    		@RequestParam("companyName")  String companyName){
    	if (Utils.isBlankOrEmpty(String.valueOf(catId)) && Utils.isBlankOrEmpty(username) 
    			&& Utils.isBlankOrEmpty(companyName))
    		return new ArrayList<SearchEngineerVendor>();
    	
    	Map map = new HashMap<>();
    	if (!Utils.isBlankOrEmpty(String.valueOf(catId)))
    		map.put(EngineerVendorRepository.CATEGORY, catId);
    	if (!Utils.isBlankOrEmpty(String.valueOf(username)))
    		map.put(EngineerVendorRepository.USERNAME, username);
    	if (!Utils.isBlankOrEmpty(String.valueOf(companyName)))
    		map.put(EngineerVendorRepository.COMPANY, companyName);
        return engineerVendorService.getEngineerVendor(map);
    }
    
    @GetMapping(value = "/getSuppliersByCategory")
    public List<SearchSupplier> getSuppliersByCategoryIdAndUsernameAndCompanyName(@RequestParam("catId")  Long catId,
    		@RequestParam("username")  String username,
    		@RequestParam("companyName")  String companyName){
    	if (Utils.isBlankOrEmpty(String.valueOf(catId)) && Utils.isBlankOrEmpty(String.valueOf(username)) 
    			&& Utils.isBlankOrEmpty(String.valueOf(companyName)))
    		return new ArrayList<SearchSupplier>();
    	
    	Map map = new HashMap<>();
    	if (!Utils.isBlankOrEmpty(String.valueOf(catId)))
    		map.put(SupplierRepository.CATEGORY, catId);
    	if (!Utils.isBlankOrEmpty(String.valueOf(username)))
    		map.put(SupplierRepository.USERNAME, username);
    	if (!Utils.isBlankOrEmpty(String.valueOf(companyName)))
    		map.put(SupplierRepository.COMPANY, companyName);
        return supplierService.getSupplier(map);
    }
    
    @GetMapping(value = "/getMyListVendorsByCategory")
    public List<SearchEngineerVendor> getMyListVendorsByCategoryId(@RequestParam("catId")  Long catId){
    	if (Utils.isBlankOrEmpty(String.valueOf(catId)))
    		return new ArrayList<SearchEngineerVendor>();
    	
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	return engineerVendorService.getVendorsFromUserVendorList(customUserDetails.getUsername(), catId);
    }
    
    @GetMapping(value = "/getMyListSuppliersByCategory")
    public List<SearchSupplier> getMyListSuppliersByCategoryId(@RequestParam("catId")  Long catId){
    	if (Utils.isBlankOrEmpty(String.valueOf(catId)))
    		return new ArrayList<SearchSupplier>();
    	
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	return supplierService.getSuppliersFromUserVendorList(customUserDetails.getUsername(), catId);
    }
    
    @PostMapping(value = "/postVendorList/{vendorIds}")
    public List [] postVendorList(@PathVariable String[] vendorIds){
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	return engineerVendorService.addVendorsToUserVendorList(customUserDetails.getUsername(), vendorIds);
    }
    
    @PostMapping(value = "/postSupplierList/{supplierIds}")
    public List [] postSupplierList(@PathVariable String[] supplierIds){
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	return supplierService.addSuppliersToUserVendorList(customUserDetails.getUsername(), supplierIds);
    }
    
    @DeleteMapping(value = "/deleteVendorList/{vendorIds}")
    public List [] deleteVendorList(@PathVariable String[] vendorIds){
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	return engineerVendorService.deleteVendorsfromUserVendorList(customUserDetails.getUsername(), vendorIds);
    }
    
    @DeleteMapping(value = "/deleteSupplierList/{supplierIds}")
    public List [] deleteSupplierList(@PathVariable String[] supplierIds){
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	return supplierService.deleteSuppliersfromUserVendorList(customUserDetails.getUsername(), supplierIds);
    }
}
