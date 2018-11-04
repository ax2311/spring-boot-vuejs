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
@Table(name="SUPPLIER")
public class Supplier extends BaseCompany {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="supplier_id")
	 private Long supplierId;
	 
	 @Column(name="num_of_employer")
	 private int numOfEmployer;
	 
	 @OneToMany( cascade=CascadeType.ALL)
	 @JoinColumn(name = "supplier_id")
	 @LazyCollection(LazyCollectionOption.FALSE)
	 private List<CompanyCategory> companyCategory;
	 
	 public Supplier(){
		 
	 }

	public Supplier(String companyName, String address, District district, String mailAddress, String phoneNum,
			String email, int numOfEmployer, String description, byte[] profilePhoto, List<CompanyCategory> companyCategories) {
		super(companyName,address,mailAddress,district,phoneNum,email,description,profilePhoto);		
		this.numOfEmployer = numOfEmployer;		
		this.companyCategory = companyCategories;
		
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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
