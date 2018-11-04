package com.springBlog.entities;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseCompany {

    @Column(name="company_name")
	 private String companyName;
	 
    @Column(name="address")
	 private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="district_id")
	 private District district;
	 
	 @Column(name="mail_address")
	 private String mailAddress;
	 
	 @Column(name="phone_num")
	 private String phoneNum;
	 
	 @Column(name="email")
	 private String email;
	 	 
	 @Column(name="description")
	 private String description;
	 
	 @Column(name="profile_photo")
	 private byte[] profilePhoto;
	 
	 public BaseCompany(){
		 
	 }
	 
	 public BaseCompany(String companyName, String address, String mailAddress, District district, String phoneNum, String email, 
			 String description ,byte[] profilePhoto){
		this.companyName = companyName;
		this.address = address;
		this.district = district;
		this.mailAddress = mailAddress;
		this.phoneNum = phoneNum;
		this.email = email;
		this.description = description;
		this.profilePhoto = profilePhoto;
	 }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
}
