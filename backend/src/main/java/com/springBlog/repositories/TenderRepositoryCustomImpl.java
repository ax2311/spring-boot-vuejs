package com.springBlog.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springBlog.pojo.SearchCurrentTender;
import com.springBlog.pojo.SearchEngineerVendor;
import com.springBlog.pojo.SearchReceivedTender;
import com.springBlog.pojo.SearchTenderDetail;

public class TenderRepositoryCustomImpl implements TenderRepositoryCustom{
	@Autowired
    private EntityManager em;
	
	@Transactional
	@Override
	public
	List<SearchTenderDetail> findDetailsyUsernameAndTenderId(String username, Long tenderId, String companyType){
		List<SearchTenderDetail> results = new ArrayList<SearchTenderDetail>();
		
		String joinBy = null;
		String selectClause = null;
		
		if ("E".equalsIgnoreCase(companyType)){
			joinBy = " inner join engineer_vendor ev " +
					" on ev.engineer_vendor_id = td.engineer_vendor_id " +
					" inner join users u2 " +
					" on ev.engineer_vendor_id = u2.engineer_vendor_id ";
			
			selectClause = " ,ev.company_name as companyName ";
		} else if ("S".equalsIgnoreCase(companyType)){
			joinBy = " inner join supplier s " +
					" on s.supplier_id = td.supplier_id " +
					" inner join users u2 " +
					" on s.supplier_id = u2.supplier_id ";
			
			selectClause = " ,s.company_name as companyName ";
		}
		
		String sql = " select td.tender_detail_id as tenderDetailId " +
				" , td.accepted as accepted " +
				" , td.remarks as remarks " +
				" ,td.price as price " +
				" ,u2.user_name as username " +
				" ,td.bidded as bidded " +
				selectClause +
				" from tenders t   " +
				" inner join tender_details td " +
				" on t.tender_id = td.tender_id  " +
				" inner join property_management pm  " + 
				" on t.property_management_id = pm.property_management_id   " +
				" inner join users u   " +
				" on u.property_management_id = pm.property_management_id   " +
				joinBy +
				" WHERE   " +
				" u.user_name = :username " +
				" and t.tender_id = :tenderId " +
				" and t.company_type =  :companyType " +
				" order by td.tender_detail_id desc ";
		
		Query query = em.createNativeQuery(sql, "SearchTenderDetailXml");

		query.setParameter("username", username);
		query.setParameter("tenderId", tenderId);
		query.setParameter("companyType", companyType);
				
		results = query.getResultList();

		return  results;
	}

	@Transactional
	@Override
	public List<SearchReceivedTender> findByIdAndType(long id, String type){
		List<SearchReceivedTender> results = new ArrayList<SearchReceivedTender>();
		
		String joinBy = null;
		String whereClause = null;
		if ("E".equals(type)){
			joinBy = "inner join engineer_vendor ev " +
					"on ev.engineer_vendor_id = td.engineer_vendor_id ";
			
			whereClause = "ev.engineer_vendor_id = ";
		}else if ("S".equals(type)){
			joinBy = "inner join supplier s " +
					"on s.supplier_id = td.supplier_id ";
			
			whereClause = "s.supplier_id = ";
		}
		
		String sql = "select td.tender_detail_id as tenderDetailId,u.user_name as username, pm.company_name as company "+
		            " , t.title as title, DATE_FORMAT(t.from_date, '%d-%m-%Y %H:%i') as fromDate " + 
		            " ,DATE_FORMAT(t.to_date, '%d-%m-%Y %H:%i') as toDate ,t.minimum_price as minimumPrice " +
				    " ,t.maximum_price as maximumPrice, DATE_FORMAT(t.date_expired, '%d-%m-%Y %H:%i') as dateExpired, "+
		            " DATE_FORMAT(t.date_added, '%d-%m-%Y %H:%i') as dateAdded " +
					"from tender_details td " +
					joinBy +
					"inner join tenders t " +
					"on t.tender_id = td.tender_id " +
					"inner join property_management pm " +
					"on t.property_management_id = pm.property_management_id " +
					"inner join users u " +
					"on u.property_management_id = pm.property_management_id " +
					"WHERE " +
					whereClause + " :id " +
					" order by td.tender_detail_id desc ";	
			
		Query query = em.createNativeQuery(sql, "SearchReceivedTenderXml");

		query.setParameter("id", id);
				
		results = query.getResultList();

		return  results;
	}
	
	@Transactional
	@Override
	public List<SearchCurrentTender> findByPmUsername(String username){
		List<SearchCurrentTender> results = new ArrayList<SearchCurrentTender>();
		
		String sql = "select t.tender_id as tenderId " +
					 ", t.title as title, DATE_FORMAT(t.from_date, '%d-%m-%Y %H:%i') as fromDate " +
					 ",DATE_FORMAT(t.to_date, '%d-%m-%Y %H:%i') as toDate ,t.minimum_price as minimumPrice  " +
					 ",t.maximum_price as maximumPrice, DATE_FORMAT(t.date_expired, '%d-%m-%Y %H:%i') as dateExpired,  " +
					 "DATE_FORMAT(t.date_added, '%d-%m-%Y %H:%i') as dateAdded, t.company_type as companyType  " +
					"from tenders t  " +
					"inner join property_management pm  " +
					"on t.property_management_id = pm.property_management_id  " +
					"inner join users u  " +
					"on u.property_management_id = pm.property_management_id  " +
					"WHERE  " +
					"u.user_name = :username " +
					 "order by t.tender_id desc ";
		
		Query query = em.createNativeQuery(sql, "SearchCurrentTenderXml");

		query.setParameter("username", username);
				
		results = query.getResultList();

		return  results;
	}
}
