package com.springBlog.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.springBlog.entities.Category;
import com.springBlog.pojo.SearchEngineerVendor;

public class EngineerVendorRepositoryImpl implements EngineerVendorRepositoryCustom{
	 @PersistenceContext
	 private EntityManager em;
	    
	@Override
	@Transactional
	public List<SearchEngineerVendor> findEngineerVendorsByCategoryId(Map<String,String> searchValue) {
		// TODO Auto-generated method stub
		List<SearchEngineerVendor> results = new ArrayList<SearchEngineerVendor>();
		
		String sql = "select ev.engineer_vendor_id as engineerVendorId,c.category as category, u.user_name as username " +
				", ev.company_name as company, ev.description as description " +
				", d.district as district, ev.email as email, ev.phone_num as phone" +
				" from users u inner join engineer_vendor ev  " +
				" on u.engineer_vendor_id = ev.engineer_vendor_id " +
				" inner join company_category cc " +
				" on ev.engineer_vendor_id = cc.engineer_vendor_id " +
				" inner join category c " +
				" on cc.category_id = c.category_id " +
				" left join district d " +
				" on ev.district_id = d.district_id " +
				" where " +
				" c.company_type = 'E' ";				
		
		if (searchValue == null || searchValue.size() == 0)
			return results;
		
		if (searchValue.get(EngineerVendorRepository.CATEGORY) != null)
			sql += " and c.category_id = :categoryId ";
		if (searchValue.get(EngineerVendorRepository.COMPANY) != null)
			sql += " and ev.company_name = :companyName ";
		if (searchValue.get(EngineerVendorRepository.USERNAME) != null)
			sql += " and u.user_name = :username ";
		
		sql += " order by ev.engineer_vendor_id";
			
		Query query = em.createNativeQuery(sql, "SearchEngineerVendorXml");
		
		if (searchValue.get(EngineerVendorRepository.CATEGORY) != null)
			query.setParameter("categoryId", searchValue.get(EngineerVendorRepository.CATEGORY));
		if (searchValue.get(EngineerVendorRepository.COMPANY) != null)
			query.setParameter("companyName", searchValue.get(EngineerVendorRepository.COMPANY));
		if (searchValue.get(EngineerVendorRepository.USERNAME) != null)
			query.setParameter("username", searchValue.get(EngineerVendorRepository.USERNAME));
				
		results = query.getResultList();

		return  results;
	}

}
