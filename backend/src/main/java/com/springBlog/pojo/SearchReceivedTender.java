package com.springBlog.pojo;

import java.util.Date;

public class SearchReceivedTender {
    Long tenderDetailId;
    
    String username;
    
    String company;
    
    String title;
    
    String fromDate;
    
    String toDate;
    
    int minimumPrice;
    
    int maximumPrice;
    
    String dateAdded;
    
    String dateExpired;

	public SearchReceivedTender(Long tenderDetailId, String username, String company, String title, String fromDate,
			String toDate, int minimumPrice, int maximumPrice, String dateAdded, String dateExpired) {
		super();
		this.tenderDetailId = tenderDetailId;
		this.username = username;
		this.company = company;
		this.title = title;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.dateAdded = dateAdded;
		this.dateExpired = dateExpired;
	}

	public Long getTenderDetailId() {
		return tenderDetailId;
	}

	public void setTenderDetailId(Long tenderDetailId) {
		this.tenderDetailId = tenderDetailId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}

}
