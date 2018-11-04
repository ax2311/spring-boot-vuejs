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
import javax.persistence.Table;

import com.springBlog.entities.Category;
import com.springBlog.entities.Supplier;
import com.springBlog.entities.EngineerVendor;

@Entity
@Table(name = "COMPANY_CATEGORY")
public class CompanyCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="company_category_id")
	Long companyCategoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	Category category;

	public Long getCompanyCategoryId() {
		return companyCategoryId;
	}

	public void setCompanyCategoryId(Long companyCategoryId) {
		this.companyCategoryId = companyCategoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
