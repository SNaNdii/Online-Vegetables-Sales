package com.masai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.masai.exceptions.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.service.AdminService;

public class AdminController {

	private AdminService adminSer;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> addAdminHandler(@RequestBody Admin admin) throws CustomerException{
		
		Admin a = adminSer.addAdmin(admin);
	
		return new ResponseEntity<>(a,HttpStatus.CREATED);
		
		
	}
}
