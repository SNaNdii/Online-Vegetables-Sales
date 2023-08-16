package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.model.CurrentAdminUserSession;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.Feedback;
import com.masai.model.Orders;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.FeedbackDao;
import com.masai.repository.OrdersDao;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private OrdersDao ordersDao;

	@Override
	public Feedback addFeedBack(Feedback feedback, String key, Integer orderId) throws FeedbackException{

		    CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
			
			if(cus==null) {
				throw new FeedbackException("Please login first");
			}
			
			Optional<Customer>opt=customerDao.findById(cus.getUserId());
			
			Customer customer=opt.get();
			
			
			Optional<Orders>op=ordersDao.findById(orderId);
			
			if(op.isPresent())
			{
				
				Orders orders=op.get();
				
				
				
				
				feedback.setOrders(orders);
				feedback.setCustomer(customer);
				
				Feedback fb2=feedbackDao.save(feedback);
				
				orders.setFeedback(fb2);
				customer.getFeedbacks().add(fb2);
				
				return fb2;
				
				
			}
			else
			{
				throw new FeedbackException("No order is found with this order id");
			}
		
		
	}

	@Override
	public Feedback viewAllFeedBack(Integer orderId, String key) throws FeedbackException {
		
		   CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
			
		    if(cus==null) {
			throw new FeedbackException("Please login first");
			}
		    
		    Optional<Orders>op=ordersDao.findById(orderId);
					
		    if(op.isPresent())
			{
		    	Orders orders=op.get();
		    	
		    	Feedback fb=orders.getFeedback();
		    	
		    	return fb;
			}
		    else
		    {
		    	throw new FeedbackException("No order is found with this order id");
		    }
		
		
		
	}

	@Override
	public List<Feedback> viewFeedBack(Integer customerId, String key) throws FeedbackException, CustomerException {
		
		CurrentAdminUserSession aus=adminSessionDao.findByUuid(key);
		
	    if(aus==null) {
		throw new FeedbackException("Please login first as an admin");
		}
	    
	   Optional<Customer> op=customerDao.findById(customerId);
	   
	   if(op.isPresent())
	   {
		  Customer customer=op.get(); 
		  
		 List<Feedback>arr= customer.getFeedbacks();
		 
		 
		 if(arr.isEmpty())
		 {
			 throw new FeedbackException("feedback list is empty");
		 }
		 
		 return arr;
		   
	   }
	   else
	   {
		   throw new FeedbackException("No customer is found with this customer id");  
	   }
		
		
	}

	
	}

