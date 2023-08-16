package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentAdminUserSession;


public interface AdminSessionDao extends JpaRepository<CurrentAdminUserSession,Integer>{
	
	public CurrentAdminUserSession findByUuid(String key);

}
