package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.exceptions.CustomerException;
import com.masai.model.Cart;
import com.masai.model.CurrentAdminUserSession;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private CartDao cartDao;

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerException {
		
		Customer c=customerDao.findByEmail(email);
		
		if(c==null)
		{
			throw new CustomerException("No customer is there with email"+email);
		}
		
		return c;
		
		
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
		Customer c=customerDao.findByEmail(customer.getEmail());
		
		if(c!=null)
		{
			throw new CustomerException("Customer already Exist with this email");
		}
		
		if(customer.getPassword().equals(customer.getConfirmPassword()))
		{
			
			
			Customer new_customer=customerDao.save(customer);
			
			Cart cr=new Cart();
			cr.setCustomer(new_customer);
			new_customer.setCart(cr);
			
			cartDao.save(cr);
			
			return new_customer;
		}
		else
		{
			throw new CustomerException("Password and Confirm Password did not matched");
		}
		
		
	}
	
	
	
	
	

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		// TODO Auto-generated method stub
		
		Customer c=customerDao.findByEmail(customer.getEmail());
		
		if(c==null)
		{
			throw new CustomerException("Please enter a valid email id");
		}
		
		  CurrentCustomerUserSession cus= customerSessionDao.findByUuid(key);
		  
		  if(cus==null)
		  {
			  throw new CustomerException("Please login first as an customer");
		  }
		  
		  Customer ct=customerDao.save(customer);
		  
		return ct;
	}

	@Override
	public String removeCustomer(String email, String key) throws CustomerException {
	
		
		  CurrentAdminUserSession aus= adminSessionDao.findByUuid(key);
		  
		  if(aus==null)
		  {
			  throw new CustomerException("No Admin Found With This Key");  
		  }
		  
		  
		  Customer c=customerDao.findByEmail(email);
			
			if(c==null)
			{
				throw new CustomerException("Customer does not exist");
			}
		  
		  
		   customerDao.delete(c);
		   
		   return "Deleted....";
		
		
		
		
	}

	@Override
	public Customer viewCustomer(String email, String key) throws CustomerException {
	
		
           CurrentAdminUserSession aus= adminSessionDao.findByUuid(key);
		  
		  if(aus==null)
		  {
			  throw new CustomerException("No Admin Found With This Key");  
		  }
		  
		  
		  Customer c=customerDao.findByEmail(email);
			
			if(c==null)
			{
				throw new CustomerException("Customer does not exist");
			}
			
			
			return  c;
		  
		
		
	}

	@Override
	public List<Customer> viewCustomerList(String key) throws CustomerException {
	
		 CurrentAdminUserSession aus= adminSessionDao.findByUuid(key);
		  
		  if(aus==null)
		  {
			  throw new CustomerException("No Admin Found With This Key");  
		  }
		  
		    List<Customer> arr=customerDao.findAll();
		  
		    
		    return arr;
		    
		
		
	}
	


}
