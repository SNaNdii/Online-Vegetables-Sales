package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {
	
	    public Customer getCustomerByEmail(String email) throws CustomerException;
	    
	    public Customer addCustomer(Customer customer) throws CustomerException;
	    
	    public Customer updateCustomer(Customer customer, String key) throws CustomerException;
	    
	    public String removeCustomer(String email, String key) throws CustomerException;
	    
	    public Customer viewCustomer(String email, String key) throws CustomerException;
	    
	    public List<Customer> viewCustomerList(String key) throws CustomerException;
       
}
