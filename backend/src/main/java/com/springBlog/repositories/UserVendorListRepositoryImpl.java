package com.springBlog.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springBlog.Utils;
import com.springBlog.entities.Role;
import com.springBlog.entities.User;
import com.springBlog.entities.UserRoles;
import com.springBlog.entities.UserVendorList;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;

public class UserVendorListRepositoryImpl implements  UserVendorListRepositoryCustom{
	@Autowired
    private EntityManager em;
    
    @Override
    @Transactional
    public UserVendorList save (UserVendorList userVendorList) {
    	em.merge(userVendorList);
    	
        return userVendorList;
    }
    
    @Override
    @Transactional
    public List<SearchEngineerVendor> findEngineerVendorsByUsernameByCategoryId(String username, long catId) {
    	List<SearchEngineerVendor> results = new ArrayList<SearchEngineerVendor>();
    	if (Utils.isBlankOrEmpty(username) || Utils.isBlankOrEmpty(String.valueOf(catId)))
    		return results;
		
		String sql = "select ev.engineer_vendor_id as engineerVendorId,c.category as category, u.user_name as username " +
				", ev.company_name as company, ev.description as description " +
				", d.district as district, ev.email as email, ev.phone_num as phone" +
				" from users u inner join engineer_vendor ev  " +
				" on u.engineer_vendor_id = ev.engineer_vendor_id " +
				" inner join company_category cc " +
				" on ev.engineer_vendor_id = cc.engineer_vendor_id " +
				" inner join category c " +
				" on cc.category_id = c.category_id " +
				" inner join user_vendor_list uvl " +
				" on uvl.engineer_vendor_id = ev.engineer_vendor_id " +
				" inner join property_management pm " +
				" on pm.property_management_id = uvl.property_management_id " +
				" inner join users u2 " +
				" on u2.property_management_id = pm.property_management_id " +
				" left join district d " +
				" on ev.district_id = d.district_id " +
				" where " +
				" c.company_type = 'E' " +
				" and u2.user_name = :username " +
				" and c.category_id = :categoryId " +
				" order by ev.engineer_vendor_id ";
			
		Query query = em.createNativeQuery(sql, "SearchEngineerVendorXml");
		
		query.setParameter("categoryId", catId);
		query.setParameter("username", username);
				
		results = query.getResultList();

		return  results;
    }
    
    @Override
    @Transactional
    public List<SearchSupplier> findSuppliersByUsernameByCategoryId(String username, long catId) {
    	List<SearchSupplier> results = new ArrayList<SearchSupplier>();
    	if (Utils.isBlankOrEmpty(username) || Utils.isBlankOrEmpty(String.valueOf(catId)))
    		return results;
		
		String sql = "select s.supplier_id as supplierId,c.category as category, u.user_name as username " +
				", s.company_name as company, s.description as description " +
				", d.district as district, s.email as email, s.phone_num as phone" +
				" from users u inner join supplier s  " +
				" on u.supplier_id = s.supplier_id " +
				" inner join company_category cc " +
				" on s.supplier_id = cc.supplier_id " +
				" inner join category c " +
				" on cc.category_id = c.category_id " +
				" inner join user_vendor_list uvl " +
				" on uvl.supplier_id = s.supplier_id " +
				" inner join property_management pm " +
				" on pm.property_management_id = uvl.property_management_id " +
				" inner join users u2 " +
				" on u2.property_management_id = pm.property_management_id " +
				" left join district d " +
				" on s.district_id = d.district_id " +
				" where " +
				" c.company_type = 'S' " +
				" and u2.user_name = :username " +
				" and c.category_id = :categoryId " +
				" order by s.supplier_id ";
			
		Query query = em.createNativeQuery(sql, "SearchSupplierXml");
		
		query.setParameter("categoryId", catId);
		query.setParameter("username", username);
				
		results = query.getResultList();

		return  results;
    }
}
