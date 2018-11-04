package com.springBlog.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.service.UserService;

@RestController
public class ProfileController {
	
	@Autowired
	UserService userService;

    @GetMapping(value ="/getProfile")
    public Map<String, Object> getProfile(@RequestParam String username, boolean myProfileFlag){
    	return userService.getProfileByUsername(username, myProfileFlag);
    }
    
    @GetMapping(value ="/getMyProfile")
    public Map<String, Object> getMyProfile(){
    	CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return getProfile(customUserDetails.getUsername(), true);
    }
}
