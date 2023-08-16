package com.masai.service;

import com.masai.exceptions.BillingDetailsException;
import com.masai.model.BillingDetails;

public interface BillingsService {

	public BillingDetails addBill(Integer orderId, String mode,String key) throws BillingDetailsException;
	
	//public BillingDetails updateBill(BillingDetails bill) throws BillingDetailsException;
	
	public BillingDetails viewBill(Integer orderId, String key) throws BillingDetailsException;
}