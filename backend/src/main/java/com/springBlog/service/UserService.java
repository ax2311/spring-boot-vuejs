package com.springBlog.service;

import com.springBlog.entities.User;
import com.springBlog.pojo.Profile;
import com.springBlog.entities.BaseCompany;
import com.springBlog.entities.CompanyCategory;
import com.springBlog.entities.District;
import com.springBlog.entities.EngineerVendor;
import com.springBlog.entities.PropertyManagement;
import com.springBlog.entities.Role;
import com.springBlog.entities.Supplier;
import com.springBlog.repositories.RoleRepository;
import com.springBlog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user, List<Role> roleList){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.create(user, roleList);
    }
    
    public void updateProfile(String username, Profile profile, District district, List<CompanyCategory> companyCategories) throws IOException{
        User user;
        user = getUser(username);
        if (user.getEngineerVendor() != null){
        	EngineerVendor ev = user.getEngineerVendor();
        	ev.setAddress(profile.getAddress());
        	ev.setCompanyName(profile.getCompanyName());
        	ev.setCompanyCategory(companyCategories);
        	ev.setDescription(profile.getDescription());
        	ev.setDistrict(district);
        	ev.setEmail(profile.getEmail());
        	ev.setMailAddress(profile.getMailAddress());
        	ev.setNumOfEmployer(profile.getNumOfEmployer()==null || profile.getNumOfEmployer().isEmpty() ? 0 : Integer.valueOf(profile.getNumOfEmployer()));
        	ev.setPhoneNum(profile.getPhoneNum());
        	if (profile.getProfilePhoto() != null)
        		ev.setProfilePhoto(profile.getProfilePhoto().getBytes());
        } else if (user.getPropertyManagement() != null){
        	PropertyManagement pm = user.getPropertyManagement();
        	pm.setAddress(profile.getAddress());
        	pm.setCompanyName(profile.getCompanyName());
        	pm.setDescription(profile.getDescription());
        	pm.setDistrict(district);
        	pm.setEmail(profile.getEmail());
        	pm.setMailAddress(profile.getMailAddress());
        	pm.setPhoneNum(profile.getPhoneNum());
        	if (profile.getProfilePhoto() != null)
        		pm.setProfilePhoto(profile.getProfilePhoto().getBytes());
        } else if (user.getSupplier() != null) {
        	Supplier sp = user.getSupplier();
        	sp.setAddress(profile.getAddress());
        	sp.setCompanyName(profile.getCompanyName());
        	sp.setCompanyCategory(companyCategories);
        	sp.setDescription(profile.getDescription());
        	sp.setDistrict(district);
        	sp.setEmail(profile.getEmail());
        	sp.setMailAddress(profile.getMailAddress());
        	sp.setNumOfEmployer(profile.getNumOfEmployer()==null || profile.getNumOfEmployer().isEmpty() ? 0 : Integer.valueOf(profile.getNumOfEmployer()));
        	sp.setPhoneNum(profile.getPhoneNum());
        	if (profile.getProfilePhoto() != null)
        		sp.setProfilePhoto(profile.getProfilePhoto().getBytes());
        }
        
        userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Role getRoleByRoleName(String roleName) {
    	return roleRepository.findByName(roleName);
    }
    
    public List<Role> getRoleByUsername(String username) {
    	return roleRepository.findRoleByUsername(username);
    }
    
    public Map<String, Object> getProfileByUsername(String username, boolean myProfileFlag) {
    	User user = getUser(username);
    	Map<String, Object> map = new HashMap<String, Object>();
    	
        if (user.getEngineerVendor() != null){
        	
        	EngineerVendor ev = user.getEngineerVendor();
        	setCommonProfile(map, ev, myProfileFlag);
        	map.put("numOfEmployer",nullToEmpty(ev.getNumOfEmployer()));
        	if (myProfileFlag)
        		map.put("category",ev.getCompanyCategory().get(0).getCategory().getCategoryId());
        	else
        		map.put("category",ev.getCompanyCategory().get(0).getCategory().getCategory());
        } else if (user.getPropertyManagement() != null){
        	
        	PropertyManagement pm = user.getPropertyManagement();
        	setCommonProfile(map, pm, myProfileFlag);
        	map.put("numOfEmployer", "");
        	map.put("category","");
        	
        } else if (user.getSupplier() != null) {
 
        	Supplier sp = user.getSupplier();
        	setCommonProfile(map, sp, myProfileFlag);
        	map.put("numOfEmployer",nullToEmpty(sp.getNumOfEmployer()));
        	if (myProfileFlag)
        		map.put("category",sp.getCompanyCategory().get(0).getCategory().getCategoryId());
        	else
        		map.put("category",sp.getCompanyCategory().get(0).getCategory().getCategory());
        }
    	
		return map;
    }
    
    private void setCommonProfile(Map<String,Object> map, Object object, boolean myProfileFlag) {
    	map.put("address", nullToEmpty(((BaseCompany) object).getAddress()));
    	map.put("companyName", nullToEmpty(((BaseCompany) object).getCompanyName()));
    	map.put("mailAdress",nullToEmpty(((BaseCompany) object).getMailAddress()));
    	if (myProfileFlag && ((BaseCompany) object).getDistrict() != null)
    		map.put("district",((BaseCompany) object).getDistrict().getDistrictId());
    	else if (((BaseCompany) object).getDistrict() != null)
    		map.put("district",((BaseCompany) object).getDistrict().getDistrict());
    	else
    		map.put("district", "");
    	map.put("phoneNum",nullToEmpty(((BaseCompany) object).getPhoneNum()));
    	map.put("email",nullToEmpty(((BaseCompany) object).getEmail()));
    	map.put("description",nullToEmpty(((BaseCompany) object).getDescription()));
    	map.put("profilePhoto",nullToEmpty(((BaseCompany) object).getProfilePhoto()));
    }
    
    private Object nullToEmpty(Object obj){
    	if (obj == null)
    		return "";
    	else
    		return obj;
    }
}