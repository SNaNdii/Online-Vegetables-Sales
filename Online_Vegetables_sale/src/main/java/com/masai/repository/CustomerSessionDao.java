package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentCustomerUserSession;

public interface CustomerSessionDao extends JpaRepository<CurrentCustomerUserSession,Integer> {
	
	public CurrentCustomerUserSession findByUuid(String key);

}