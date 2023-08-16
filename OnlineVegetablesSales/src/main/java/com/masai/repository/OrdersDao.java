package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>{
	
	@Query("select o from Orders o where customerId=?1")
	public List<Orders> viewAllOrderByCustomerid(Integer customerId);
	
//	@Query("select o from Orders o where =?1")
//	public List<Orders> viewAllOrderByDate(LocalDate date);
	
}
