package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.model.Feedback;

public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback, String key, Integer orderId) throws FeedbackException, CustomerException;
	
	public Feedback viewAllFeedBack(Integer orderId,String key)throws FeedbackException;
	
	public List<Feedback> viewFeedBack(Integer customerId,String key) throws FeedbackException,CustomerException;
}
