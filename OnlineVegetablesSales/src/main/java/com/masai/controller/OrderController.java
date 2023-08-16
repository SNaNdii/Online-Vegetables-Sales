package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.OrderException;
import com.masai.model.Orders;
import com.masai.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/addOrder")
	public ResponseEntity<Orders> addOrderhandler(@RequestParam String key) throws OrderException{
		
		Orders order = orderService.addOrder(key);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/viewOrder")
	public ResponseEntity<Orders> viewOrderhandler(@RequestParam Integer orderId, @RequestParam String key) throws OrderException{
		
		Orders orders = orderService.viewOrder(orderId, key);
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/viewAllOrdersByCustomerId")
	public ResponseEntity<List<Orders>> viewAllOrdersByCustomerIdhandler(@RequestParam Integer customerId, @RequestParam String key) throws OrderException{
		
		List<Orders>arr=orderService.viewAllOrdersByCustomerId(customerId, key);
		
		return new ResponseEntity<>(arr,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/cancelOrder")
	public ResponseEntity<Orders> cancelOrderhandler(@RequestParam Integer orderId, @RequestParam String key) throws OrderException{
		
		Orders orders=orderService.cancelOrder(orderId, key);
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
		
	}	
	
}