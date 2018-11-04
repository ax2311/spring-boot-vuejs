package com.springBlog.pojo;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class UserRegistration {

	@NotBlank(message = "User name cannot be empty")
	@Size(min = 5, max = 20, message 
    = "User name must be between 5 and 20 characters")	
	@Pattern(regexp = "[a-zA-Z0-9]+?",message="User name should have English letters and number only")
    private String username;
	
	@NotBlank(message = "Password cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9]+?",message="Password should have English letters and number only")
	@Size(min = 5, max = 20, message 
    = "Password must be between 5 and 20 characters")
    private String password;
	
	@NotBlank(message = "Confirmation password cannot be empty")
	@Size(min = 5, max = 20, message 
    = "Password must be between 5 and 20 characters")
    private String passwordConfirmation;
	
	@NotBlank(message = "Account type cannot be empty")
    private String accType;
	
	@NotBlank(message = "Company name cannot be empty")
	@Size(min = 3, max = 30, message 
    = "Company name must be between 3 and 30 characters")
    private String companyName;
	
    private List<String> categories;
    
    @Size(max = 300, message 
    	    = "Address is at most 300 characters")
    private String address;
    
    @Size(max = 300, message 
    	    = "Mail Address is at most 300 characters")
    private String mailAddress;

    @Pattern(regexp = "[0-9]{0,15}?",message="Phone number should have less than 15 digits")
    private String phoneNum;
    
    @Size(max = 20, message 
    	    = "email Address is at most 20 characters")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
        
    private String district;
    
    @Pattern(regexp = "[0-9]{0,5}?",message="Number of employer should be integer")
    private String numOfEmployer;
    
    @Size(max = 1000, message 
    	    = "Description is at most 1000 characters")
    private String description;
    
    private MultipartFile profilePhoto;

    public UserRegistration() {
    }

    public UserRegistration(String username, String password, String passwordConfirmation, String accType, String companyName,
    		List<String> categories, String address, String mailAddress, String phoneNum, String email, String district, String numOfEmployer
    		,String description, MultipartFile profilePhoto) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.accType = accType;
        this.companyName = companyName;
        this.categories = categories;
        this.address = address;
        this.mailAddress = mailAddress;
        this.phoneNum = phoneNum;
        this.email = email;
        this.district = district;
        if (numOfEmployer != null && !numOfEmployer.isEmpty())
        this.numOfEmployer = numOfEmployer;
        this.description = description;
        this.profilePhoto = profilePhoto;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNumOfEmployer() {
		return numOfEmployer;
	}

	public void setNumOfEmployer(String numOfEmployer) {
		this.numOfEmployer = numOfEmployer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
}