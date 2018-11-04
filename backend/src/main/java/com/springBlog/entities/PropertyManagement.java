package com.springBlog.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="PROPERTY_MANAGEMENT")
public class PropertyManagement extends BaseCompany {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="property_management_id")
	 private Long propertyManagementId;
		 
	 @OneToMany( cascade=CascadeType.ALL)
	 @JoinColumn(name="property_management_id")
	 @LazyCollection(LazyCollectionOption.FALSE)
	 private List<UserVendorList> userVendorList;
	 	 
	 public PropertyManagement(){
		 
	 }

	public PropertyManagement(String companyName, String address, District district, String mailAddress, String phoneNum,
			String email, String description, byte[] profilePhoto) {
		super(companyName,address,mailAddress,district,phoneNum,email,description,profilePhoto);	
	}

	public Long getPropertyManagementId() {
		return propertyManagementId;
	}

	public void setPropertyManagementId(Long propertyManagementId) {
		this.propertyManagementId = propertyManagementId;
	}

	public List<UserVendorList> getUserVendorList() {
		return userVendorList;
	}

	public void setUserVendorList(List<UserVendorList> userVendorList) {
		this.userVendorList = userVendorList;
	}

}
