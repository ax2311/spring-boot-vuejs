package com.springBlog.pojo;

public class SearchTenderDetail {

	Long tenderDetailId;
	String accepted;
	String bidded;
	String remarks;
	int price;
	String username;
	String companyName;
	
	public SearchTenderDetail(Long tenderDetailId, String accepted, String bidded, String remarks, int price, String username,
			String companyName) {
		super();
		this.tenderDetailId = tenderDetailId;
		this.accepted = accepted;
		this.remarks = remarks;
		this.price = price;
		this.username = username;
		this.companyName = companyName;
		this.bidded = bidded;
	}
	
	public Long getTenderDetailId() {
		return tenderDetailId;
	}
	public void setTenderDetailId(Long tenderDetailId) {
		this.tenderDetailId = tenderDetailId;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBidded() {
		return bidded;
	}

	public void setBidded(String bidded) {
		this.bidded = bidded;
	}
}
