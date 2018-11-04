package com.springBlog.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_VENDOR_LIST")
public class UserVendorList {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_vendor_list_id")
	private Long userVendorListId;
	
	//join from pm to here save pm id
	@Column(name="property_management_id")
	private long propertyManagementId;
    
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="engineer_vendor_id")
	private EngineerVendor engineerVendor;
	
	@Column(name="company_type")
	private String companyType;
	
	public UserVendorList() {
		
	}
	
	public Long getUserVendorListId() {
		return userVendorListId;
	}

	public void setUserVendorListId(Long userVendorListId) {
		this.userVendorListId = userVendorListId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public EngineerVendor getEngineerVendor() {
		return engineerVendor;
	}

	public void setEngineerVendor(EngineerVendor engineerVendor) {
		this.engineerVendor = engineerVendor;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public long getPropertyManagementId() {
		return propertyManagementId;
	}

	public void setPropertyManagementId(long propertyManagementId) {
		this.propertyManagementId = propertyManagementId;
	}

}
