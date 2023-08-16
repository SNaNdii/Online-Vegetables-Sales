package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(BillingDetailsException.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(BillingDetailsException le,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(CartException le,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(LoginException le,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}	
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(CustomerException ce,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(OrderException oe,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandle(Exception e,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgNotValidExp(MethodArgumentNotValidException me,WebRequest wr) {
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation error");
		err.setDetails(wr.getDescription(false));
		
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
	}

}