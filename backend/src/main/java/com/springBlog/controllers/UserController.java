package com.springBlog.controllers;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.entities.Category;
import com.springBlog.entities.CompanyCategory;
import com.springBlog.entities.District;
import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.PropertyManagement;
import com.springBlog.entities.Role;
import com.springBlog.entities.Supplier;
import com.springBlog.entities.User;
import com.springBlog.pojo.Profile;
import com.springBlog.pojo.UserRegistration;
import com.springBlog.service.CategoryService;
import com.springBlog.service.DistrictService;
import com.springBlog.service.EngineerVendorService;
import com.springBlog.service.PropertyManagementService;
import com.springBlog.service.SupplierService;
import com.springBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

//user account function

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired 
    private DistrictService districtService;
    
    @Autowired 
    private CategoryService categoryService;

    @Autowired
    private TokenStore tokenStore;
    
    private Validator validator;
    
    public UserController(){
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile( Profile profile) throws IOException {
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = customUserDetails.getUsername();
    	
    	List<String> returnMsg = new ArrayList<>();
    	
        chkProfile(profile, returnMsg);
        
        District district = districtService.getDistrictByDistrictId(profile.getDistrict());
        
        List<CompanyCategory> companyCategories = new ArrayList<CompanyCategory>();
        for (String registercategory: profile.getCategories()){
        	Category category = categoryService.getCategoryByCategoryId(registercategory);
        	CompanyCategory companyCategory = new CompanyCategory();
        	companyCategory.setCategory(category);
        	companyCategories.add(companyCategory);
        }
        
        if (returnMsg != null && !returnMsg.isEmpty() && returnMsg.size() != 0)
        	return returnMsg.get(0);
        
        userService.updateProfile(username, profile, district, companyCategories);
        
        returnMsg.add("success");
                
        return returnMsg.get(0);
    }

    @PostMapping(value = "/register")
    public List<String> register( UserRegistration userRegistration) throws IOException{
    	
    	List<String> returnMsg = new ArrayList<>();
    	
    	chkRegisterForm(userRegistration, returnMsg);
                
        //pass all validation
        District district = districtService.getDistrictByDistrictId(userRegistration.getDistrict());
        
        List<CompanyCategory> companyCategories = new ArrayList<CompanyCategory>();
        for (String registercategory: userRegistration.getCategories()){
        	Category category = categoryService.getCategoryByCategoryId(registercategory);
        	CompanyCategory companyCategory = new CompanyCategory();
        	companyCategory.setCategory(category);
        	companyCategories.add(companyCategory);
        }
        
        if (returnMsg != null && !returnMsg.isEmpty() && returnMsg.size() != 0)
        	return returnMsg;
        
        PropertyManagement propertyManagement;
        EngineerVendor engineerVendor;
        Supplier supplier;
        ArrayList roleList = new ArrayList<Role>();
        if ("Property Management".equals(userRegistration.getAccType())) {
        	propertyManagement = new PropertyManagement(
        			userRegistration.getCompanyName(),
        			userRegistration.getAddress(),
        			district,
        			userRegistration.getMailAddress(),
        			userRegistration.getPhoneNum(),
        			userRegistration.getEmail(),
        			userRegistration.getDescription(),
        			userRegistration.getProfilePhoto() == null ? null: userRegistration.getProfilePhoto().getBytes()
        			);
        	roleList.add(userService.getRoleByRoleName("USER"));
        	roleList.add(userService.getRoleByRoleName("PROPERTY_MANAGEMENT"));
        	userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),
        				 propertyManagement), roleList );
        } else if ("Engineer Vendor".equals(userRegistration.getAccType())) {
        	engineerVendor = new EngineerVendor(
        			userRegistration.getCompanyName(),
        			userRegistration.getAddress(),
        			district,
        			userRegistration.getMailAddress(),
        			userRegistration.getPhoneNum(),
        			userRegistration.getEmail(),
        			(userRegistration.getNumOfEmployer() == null || userRegistration.getNumOfEmployer().isEmpty()) ? 0 : Integer.valueOf(userRegistration.getNumOfEmployer()),
        			userRegistration.getDescription(),
        			userRegistration.getProfilePhoto() == null ? null: userRegistration.getProfilePhoto().getBytes(),
        			companyCategories
        			);
        	roleList.add(userService.getRoleByRoleName("USER"));
        	roleList.add(userService.getRoleByRoleName("ENGINEER_VENDOR"));
        	userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),
        			 engineerVendor), roleList );
        	
        } else if ("Supplier".equals(userRegistration.getAccType())) {
        	supplier = new Supplier(
        			userRegistration.getCompanyName(),
        			userRegistration.getAddress(),
        			district,
        			userRegistration.getMailAddress(),
        			userRegistration.getPhoneNum(),
        			userRegistration.getEmail(),
        			(userRegistration.getNumOfEmployer() == null || userRegistration.getNumOfEmployer().isEmpty()) ? 0 : Integer.valueOf(userRegistration.getNumOfEmployer()),
        			userRegistration.getDescription(),
        			userRegistration.getProfilePhoto() == null ? null: userRegistration.getProfilePhoto().getBytes(),
        			companyCategories
        			);
        	
        	roleList.add(userService.getRoleByRoleName("USER"));
        	roleList.add(userService.getRoleByRoleName("SUPPLIER"));
        	userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),
        			 supplier), roleList );
        }
        
        returnMsg.add("user created");
        
        return returnMsg;
    }
    
    private void chkProfile(Profile profile, List<String> errorMsg){
        if("Engineer Vendor".equals(profile.getAccType())
        		|| "Supplier".equals(profile.getAccType())){
        	if(profile.getCategories() == null || profile.getCategories().isEmpty())
        		errorMsg.add("Category cannot be empty");
        }
        
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);
        
        for (ConstraintViolation<Profile> violation : violations) {
        	errorMsg.add(violation.getMessage()); 
        }
    }

    private void chkRegisterForm(UserRegistration userRegistration, List<String> errorMsg){
        if(userRegistration.getPassword() != null && 
        		(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())))
            errorMsg.add("The two passwords do not match");
        
        if(userService.getUser(userRegistration.getUsername()) != null)
        	errorMsg.add("This username already exists");
        
        if(!"Property Management".equals(userRegistration.getAccType())
        		&& !"Engineer Vendor".equals(userRegistration.getAccType())
        		&& !"Supplier".equals(userRegistration.getAccType()))
        	errorMsg.add("Account Type is incorrect");
        
        if("Engineer Vendor".equals(userRegistration.getAccType())
        		|| "Supplier".equals(userRegistration.getAccType())){
        	if(userRegistration.getCategories() == null || userRegistration.getCategories().isEmpty())
        		errorMsg.add("Category cannot be empty");
        }
        
        if (userRegistration.getProfilePhoto() != null && userRegistration.getProfilePhoto().getSize() > 65535) {
        	errorMsg.add("Profile photo should be smaller than 64KB");
        }
        
        Set<ConstraintViolation<UserRegistration>> violations = validator.validate(userRegistration);
        
        for (ConstraintViolation<UserRegistration> violation : violations) {
        	errorMsg.add(violation.getMessage()); 
        }
    }
    
    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam (value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value ="/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping(value ="/getUserRole")
    public List<String> getUserRole() {
    	List<String> roleStringList= new ArrayList<String>();
    	List<Role> roleList= new ArrayList<Role>();
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	roleList = userService.getRoleByUsername(customUserDetails.getUsername());

    	if(roleList == null || roleList.isEmpty())
    		return roleStringList;
    	
    	for (Role role: userService.getRoleByUsername(customUserDetails.getUsername()))
    		roleStringList.add(role.getName());
    	
    	return roleStringList;
    }

}