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
@Table(name="ENGINEER_VENDOR")
public class EngineerVendor extends BaseCompany {

     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     @Column(name="engineer_vendor_id")
	 private Long engineerVendorId;
	 
	 @Column(name="num_of_employer")
	 private int numOfEmployer;
	 	 
	 @OneToMany( cascade=CascadeType.ALL)
	 @JoinColumn(name = "engineer_vendor_id")
	 @LazyCollection(LazyCollectionOption.FALSE)
	 private List<CompanyCategory> companyCategory;
	 
	 public EngineerVendor(){
		 
	 }

	public EngineerVendor(String companyName, String address, District district, String mailAddress, String phoneNum,
			String email, int numOfEmployer, String description, byte[] profilePhoto, List<CompanyCategory> companyCategories) {
		super(companyName,address,mailAddress,district,phoneNum,email,description,profilePhoto);
		this.numOfEmployer = numOfEmployer;
		this.companyCategory = companyCategories;
		
	}

	public Long getEngineerVendorId() {
		return engineerVendorId;
	}

	public void setEngineerVendorId(Long engineerVendorId) {
		this.engineerVendorId = engineerVendorId;
	}

	public int getNumOfEmployer() {
		return numOfEmployer;
	}

	public void setNumOfEmployer(int numOfEmployer) {
		this.numOfEmployer = numOfEmployer;
	}

	public List<CompanyCategory> getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(List<CompanyCategory> companyCategory) {
		this.companyCategory = companyCategory;
	}
}
