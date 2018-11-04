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
@Table(name="TENDER_DETAILS")
public class TenderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tender_detail_id")
	private Long tenderDetailId;
		
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="supplier_id")
	private Supplier supplier;
	
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="engineer_vendor_id")
	private EngineerVendor engineerVendor;
	
    @Column(name="company_type")
	private String companyType;
	
    @Column(name="bidded")
	private String bidded;
	
    @Column(name="accepted")
	private String accepted;
	
    @Column(name="remarks")
	private String remarks;
	
    @Column(name="price")
	private int price;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="tender_id")
    private Tender tender;

	public void setTenderDetailId(Long tenderDetailId) {
		this.tenderDetailId = tenderDetailId;
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

	public String getBidded() {
		return bidded;
	}

	public void setBidded(String bidded) {
		this.bidded = bidded;
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

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}
}
