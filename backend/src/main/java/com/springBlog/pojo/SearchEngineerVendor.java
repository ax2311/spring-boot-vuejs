package com.springBlog.pojo;

public class SearchEngineerVendor {

	 private long engineerVendorId;
	 private String category;
	 private String username;
	 private String company;
	 private String description; 
	 private String district; 
	 private String email; 
	 private String phone;
	 
	 
	public SearchEngineerVendor(){
		
	}
	
	public SearchEngineerVendor(long engineerVendorId, String category, String username, String company, String description, String district, String email,
			String phone) {
		this.engineerVendorId = engineerVendorId;
		this.category = category;
		this.username = username;
		this.company = company;
		this.description = description;
		this.district = district;
		this.email = email;
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhoneNum(String phone) {
		this.phone = phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getEngineerVendorId() {
		return engineerVendorId;
	}

	public void setEngineerVendorId(long engineerVendorId) {
		this.engineerVendorId = engineerVendorId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
				
}
