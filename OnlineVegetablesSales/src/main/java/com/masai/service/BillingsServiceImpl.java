package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BillingDetailsException;
import com.masai.model.BillingDetails;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.TransactionMode;
import com.masai.repository.BillingDetailsDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.OrdersDao;


@Service
public class BillingsServiceImpl implements BillingsService {

	
	@Autowired
	private BillingDetailsDao billingDetailsDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public BillingDetails addBill(Integer orderId, String mode,String key) throws BillingDetailsException{
	
         CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);
		
		if(cus == null) {
			throw new BillingDetailsException("Please login first");
		}
		
		Optional<Orders>op=ordersDao.findById(orderId);
		
		if(op.isPresent())
		{
			
		Orders orders=op.get();	
		
		if(orders.getStatus().equals("PLACED"))
		{
			throw new BillingDetailsException("order is already placed");
		}
		else if(orders.getStatus().equals("CANCELLED"))
		{
			throw new BillingDetailsException("Please enter a PENDING order id");
		}
		
		BillingDetails bd=new BillingDetails();
		
		bd.setTransactionDate(LocalDateTime.now());
		bd.setTransactionMode(TransactionMode.valueOf(mode.toUpperCase()));
		bd.setTransactionStatus("SUCCESSFULL");
		
		 Optional<Customer> opt=customerDao.findById(cus.getUserId());
		 
		Customer customer= opt.get();
		
		bd.setBillingAddress(customer.getAddress());
		
		bd.setOrder(orders);
		
		BillingDetails bd2=billingDetailsDao.save(bd);
		
		orders.setStatus("PLACED");
		orders.setBillingDetail(bd2);
		
		ordersDao.save(orders);
		
		return bd2;
			
			
		}
		else
		{
			throw new BillingDetailsException("No order found with this order id");
		}
		
	
		
	}



	@Override
	public BillingDetails viewBill(Integer orderId, String key) throws BillingDetailsException{
		
	     CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);
			
			if(cus == null) {
				throw new BillingDetailsException("Please login first");
			} 
			
			
			Optional<Orders>op=ordersDao.findById(orderId);
			
			if(op.isPresent())
			{
			Orders orders=op.get();
			
			if(orders.getStatus().equals("PENDING"))
			{
				throw new BillingDetailsException("First place the order with order id "+orderId);
			}
			
			BillingDetails bd=orders.getBillingDetail();
			
			return bd;
			
			}
			else
			{
				throw new BillingDetailsException("No order found with this order id");
			}
		
	
	}

}

