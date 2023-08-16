package com.masai.service;


import java.util.List;
import com.masai.exceptions.OrderException;
import com.masai.model.Orders;


public interface OrderService {
	
	public Orders addOrder(String key) throws OrderException;
	
	public Orders viewOrder(Integer orderId,String key) throws OrderException;
	
	//public Orders updateOrderDetails(Orders order,String key) throws OrderException;
	
	public List<Orders> viewAllOrdersByCustomerId(Integer customerId,String key) throws OrderException;
	
	//public List<Orders> viewAllOrdersByDate(LocalDate date,String key) throws OrderException;
	
	public List<Orders> viewOrderList(String key) throws OrderException;
	
	public Orders cancelOrder(Integer orderId,String key)throws OrderException;
	
}