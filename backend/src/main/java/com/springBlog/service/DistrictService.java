package com.springBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBlog.entities.District;
import com.springBlog.repositories.DistrictRepository;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepository districtRepository;
	
	public District getDistrictByDistrictId(String districtId){
		if(districtId == null || districtId.isEmpty()) return null;
		return districtRepository.findByDistrictId(Long.valueOf(districtId));
	}
	
}
