package com.springBlog.entities;

import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name="TENDERS")
public class Tender {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tender_id")
	private Long tenderId;
    	
    @Column(name="company_type")
    private String companyType;

    @Column(name="title")
    private String title;
	
    @Column(name="description")
    private String description;
	
    @Column(name="from_date")
    private Date fromDate;
	
    @Column(name="to_date")
    private Date toDate;
	
    @Column(name="address")
    private String address;
	
    @Column(name="minimum_price")
    private int minimumPrice;
	
    @Column(name="maximum_price")
    private int maximumPrice;
	
    @Column(name="document")
    private byte[] document;
	
    @Column(name="date_added")
    private Date dateAdded;
	
    @Column(name="date_expired")
    private Date dateExpired;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="property_management_id")
    private PropertyManagement propertyManagement;
    
	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(int minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public int getMaximumPrice() {
		return maximumPrice;
	}

	public void setMaximumPrice(int maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	public PropertyManagement getPropertyManagement() {
		return propertyManagement;
	}

	public void setPropertyManagement(PropertyManagement propertyManagement) {
		this.propertyManagement = propertyManagement;
	}
	
}
