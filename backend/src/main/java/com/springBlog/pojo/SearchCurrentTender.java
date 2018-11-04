package com.springBlog.pojo;

public class SearchCurrentTender {
    Long tenderId;
    
    String title;
    
    String fromDate;
    
    String toDate;
    
    int minimumPrice;
    
    int maximumPrice;
    
    String dateAdded;
    
    String dateExpired;
    
    String companyType;

	public SearchCurrentTender(Long tenderId, String title, String fromDate,
			String toDate, int minimumPrice, int maximumPrice, String dateAdded, String dateExpired, String companyType) {
		super();
		this.tenderId = tenderId;
		this.title = title;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.dateAdded = dateAdded;
		this.dateExpired = dateExpired;
		this.companyType = companyType;
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
}
