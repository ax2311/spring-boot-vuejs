package com.springBlog.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
	private Long categoryId;
	
    @Column(name="category")
	private String category;
	
    @Column(name="company_type")
	private char companyType;   
        
    public Category(){
    	
    }

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public char getCompanyType() {
		return companyType;
	}

	public void setCompanyType(char companyType) {
		this.companyType = companyType;
	}
}
