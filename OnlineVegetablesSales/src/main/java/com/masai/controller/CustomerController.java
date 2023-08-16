package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomerHandler(@RequestBody Customer customer) throws CustomerException{
		
		Customer c=customerService.addCustomer(customer);
	
		return new ResponseEntity<>(c,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer,@RequestParam String key) throws CustomerException{
		
		Customer c=customerService.updateCustomer(customer, key);
	
		return new ResponseEntity<>(c,HttpStatus.OK);
		
		
	}
	
	
	
	@DeleteMapping("/customer/{email}")
	public ResponseEntity<String> removeCustomerHandler(@PathVariable String email,@RequestParam String key) throws CustomerException{
		
		String result=customerService.removeCustomer(email, key);
	
		return new ResponseEntity<>(result,HttpStatus.OK);
		
		
	}
	
	
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable String email,@RequestParam String key) throws CustomerException{
		
		Customer c=customerService.viewCustomer(email, key);
	
		return new ResponseEntity<>(c,HttpStatus.OK);
		
		
	}	
	
	
	@GetMapping("customers/")	
    public ResponseEntity<List<Customer>> viewCustomerListHandler(@RequestParam String key) throws CustomerException{
		
		List<Customer> arr=customerService.viewCustomerList(key);
	
		return new ResponseEntity<>(arr,HttpStatus.OK);
		
		
	}	
	

}
