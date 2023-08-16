package com.masai.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.OrderException;
import com.masai.model.Cart;
import com.masai.model.CurrentAdminUserSession;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.DemandingVegetableDTO;
import com.masai.model.Orders;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.OrdersDao;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CartDao cartDao;
	
	
	@Override
	public Orders addOrder(String key) throws  OrderException{
		
		CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);
		
		if(cus == null) {
			throw new OrderException("Please login first");
		}
		
		Optional<Customer> opt = customerDao.findById(cus.getUserId()) ;
		
		Customer customer = opt.get();
		
		Cart cart=customer.getCart();
		
	    Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
	    
	    double sum=0;
	    
	    for(DemandingVegetableDTO x:arr)
	    {
	    	sum=sum+x.getPrice();
	    }
	    
	    Orders o=new Orders();
	    
	    o.setTotalAmount(sum);
	    o.setStatus("PENDING");
	    
	  
	   
	    for(DemandingVegetableDTO x:arr)
	    {
	    	o.getDemandingVegetableDto().add(x);
	    }
	    
	    
	    o.setCustomer(customer);
	    
	    Orders orders=ordersDao.save(o);
	    
	    customer.getOrders().add(orders);
	    
	    customerDao.save(customer);
	    
	    cart.setDemandingVegetableDto(null);
	    
	    cartDao.save(cart);
	    
	    
	    return orders;
		
	}

	@Override
	public Orders viewOrder(Integer orderId, String key) throws OrderException {
		
		CurrentCustomerUserSession userSession = customerSessionDao.findByUuid(key);
		
		if(userSession == null) {
			throw new OrderException("Please login first");
		}
		
	     Optional<Orders> op=ordersDao.findById(orderId);
	     
	     if(op.isPresent())
	     {
	    	 Orders orders=op.get();
	    	 
	    	 return orders;
	     }
	     else
	     {
	    	 throw new OrderException("No order found with this order id");
	     }
		
	}



	@Override
	public List<Orders> viewAllOrdersByCustomerId(Integer customerId, String key) throws OrderException {
		
		CurrentAdminUserSession aus = adminSessionDao.findByUuid(key);
		
		if(aus==null)
		{
			throw new OrderException("Please login as an admin first");
		}
		
		Optional<Customer> op=customerDao.findById(customerId);
		
		if(op.isPresent())
		{
			Customer customer=op.get();
			
			
			List<Orders>arr =customer.getOrders();
			return arr;
		}
		else
		{
			throw new OrderException("No customer found with this customer id");
		}
		
	
	}


	@Override
	public List<Orders> viewOrderList(String key) throws OrderException {
	
		CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);
		
		if(cus==null)
		{
			throw new OrderException("Please login as an customer first");
		}
		
		
        Optional<Customer> op=customerDao.findById(cus.getUserId());
	
			Customer customer=op.get();
			
			List<Orders>arr =customer.getOrders();
				
			if(arr.isEmpty())
			{
				throw new OrderException("customer did not made any order");
			}
			
	
			return arr;
			
	}

	@Override
	public Orders cancelOrder(Integer orderId, String key) throws OrderException {
		
        CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);
		
		if(cus==null)
		{
			throw new OrderException("Please login as an customer first");
		}
		
		Optional<Orders> op=ordersDao.findById(orderId);
		
		if(op.isPresent())
		{
			Orders orders=op.get();
			
			
			if(orders.getStatus().equals("PENDING"))
			{
				throw new OrderException("order did not PLACED yet");
			}
			
			orders.setStatus("CANCELLED");
			
			
			return ordersDao.save(orders);
			
			
		}
		else
		{
			throw new OrderException("No Order found with this Order Id");
		}
		
		
	}

}