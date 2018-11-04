package com.springBlog.pojo;

import com.springBlog.entities.EngineerVendor;

public class SearchVendorResult {

	private String username;
	
	private EngineerVendor engineerVendor;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public EngineerVendor getEngineerVendor() {
		return engineerVendor;
	}

	public void setEngineerVendor(EngineerVendor engineerVendor) {
		this.engineerVendor = engineerVendor;
	}

}
