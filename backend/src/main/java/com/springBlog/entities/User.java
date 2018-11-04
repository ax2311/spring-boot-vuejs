package com.springBlog.entities;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USERS")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    
    @Column(name="user_name")
    private String username;

    @JsonIgnore
    @Column(name="password")
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<UserRoles> userRoles;
    
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="engineer_vendor_id")
    private EngineerVendor engineerVendor;
    
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="property_management_id")
    private PropertyManagement propertyManagement;
    
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;
    
    User () {};

    public User(String username, String password, Object companyProfile) {
        this.username = username;
        this.password = password;
        this.userRoles = new ArrayList<UserRoles>();
        /*for (Role role: roles){
        	UserRoles userRole = new UserRoles();        	
        	userRole.setRole(role);
        	this.userRoles.add(userRole);
        }*/
        
        if (companyProfile instanceof EngineerVendor) {
        	this.setEngineerVendor((EngineerVendor)companyProfile);
        } else if (companyProfile instanceof PropertyManagement){
        	this.setPropertyManagement((PropertyManagement) companyProfile);
        } else if (companyProfile instanceof Supplier){
        	this.setSupplier((Supplier) companyProfile);
        } else {
        	throw new RuntimeException("Wrong company type");
        }
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

	public EngineerVendor getEngineerVendor() {
		return engineerVendor;
	}

	public void setEngineerVendor(EngineerVendor engineerVendor) {
		this.engineerVendor = engineerVendor;
	}

	public PropertyManagement getPropertyManagement() {
		return propertyManagement;
	}

	public void setPropertyManagement(PropertyManagement propertyManagement) {
		this.propertyManagement = propertyManagement;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}