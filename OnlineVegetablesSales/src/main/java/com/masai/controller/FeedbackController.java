package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.model.Feedback;
import com.masai.service.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	
	@PostMapping("/addFeedBack")
	public ResponseEntity<Feedback> addFeedBack(@RequestBody Feedback feedback,@RequestParam String key, @RequestParam Integer orderId) throws FeedbackException, CustomerException{
		
		
		Feedback fb=feedbackService.addFeedBack(feedback, key, orderId);
		
		return new ResponseEntity<>(fb,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/viewAllFeedBack")
	public ResponseEntity<Feedback> viewAllFeedBack(@RequestParam Integer orderId,@RequestParam String key) throws FeedbackException, CustomerException{
		
		
		Feedback fb=feedbackService.viewAllFeedBack(orderId, key);
		
		return new ResponseEntity<>(fb,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/viewFeedBackList")
	public ResponseEntity<List<Feedback>> viewFeedBackList(@RequestParam Integer customerId,@RequestParam String key) throws FeedbackException, CustomerException{
		
		
		List<Feedback> arr=feedbackService.viewFeedBack(customerId, key);
		
		return new ResponseEntity<>(arr,HttpStatus.CREATED);
	}
	

}
