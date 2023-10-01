package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.masai.exceptions.BillingDetailsException;
import com.masai.model.BillingDetails;
import com.masai.service.BillingsService;

@RestController
public class BillingDetailsController {
	
	@Autowired
	private BillingsService billingsService;
	
	
	@PostMapping("/addBill")
	public ResponseEntity<BillingDetails>addBillHandler(@RequestParam Integer orderId, @RequestParam String mode,@RequestParam String key) throws BillingDetailsException{
		
		
		BillingDetails bd=billingsService.addBill(orderId, mode, key);
		
		return new ResponseEntity<>(bd,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/viewBill")
	public ResponseEntity<BillingDetails>viewBillHandler(@RequestParam Integer orderId,@RequestParam String key) throws BillingDetailsException{
		
		
		BillingDetails bd=billingsService.viewBill(orderId, key);
		
		return new ResponseEntity<>(bd,HttpStatus.CREATED);
	}

}

