package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.BillingDetails;

public interface BillingDetailsDao extends JpaRepository<BillingDetails, Integer>{

}