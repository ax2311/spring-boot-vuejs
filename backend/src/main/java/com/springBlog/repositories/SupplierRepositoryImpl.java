package com.springBlog.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchSupplier;

public class SupplierRepositoryImpl implements SupplierRepositoryCustom{
	 @Autowired
	 private EntityManager em;
	    
	@Override
	@Transactional
	public List<SearchSupplier> findSuppliersByCategoryId(Map<String,String> searchValue) {
		// TODO Auto-generated method stub
		List<SearchSupplier> results = new ArrayList<SearchSupplier>();
		
		String sql = "select s.supplier_id as supplierId, c.category as category, u.user_name as username " +
				", s.company_name as company, s.description as description " +
				", d.district as district, s.email as email, s.phone_num as phone" +
				" from users u inner join supplier s  " +
				" on u.supplier_id = s.supplier_id " +
				" inner join company_category cc " +
				" on s.supplier_id = s.supplier_id " +
				" inner join category c " +
				" on cc.category_id = c.category_id " +
				" left join district d " +
				" on s.district_id = d.district_id " +
				" where " +
				" c.company_type = 'S' ";				
		
		if (searchValue == null || searchValue.size() == 0)
			return results;
		
		if (searchValue.get(SupplierRepository.CATEGORY) != null)
			sql += " and c.category_id = :categoryId ";
		if (searchValue.get(SupplierRepository.COMPANY) != null)
			sql += " and s.company_name = :companyName ";
		if (searchValue.get(SupplierRepository.USERNAME) != null)
			sql += " and u.user_name = :username ";
		
		sql += " order by s.supplier_id";
			
		Query query = em.createNativeQuery(sql, "SearchSupplierXml");
		
		if (searchValue.get(SupplierRepository.CATEGORY) != null)
			query.setParameter("categoryId", searchValue.get(SupplierRepository.CATEGORY));
		if (searchValue.get(SupplierRepository.COMPANY) != null)
			query.setParameter("companyName", searchValue.get(SupplierRepository.COMPANY));
		if (searchValue.get(SupplierRepository.USERNAME) != null)
			query.setParameter("username", searchValue.get(SupplierRepository.USERNAME));
				
		results = query.getResultList();

		return  results;
	}
}
