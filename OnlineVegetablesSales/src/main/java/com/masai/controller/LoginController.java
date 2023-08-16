package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.model.User;
import com.masai.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginIntoAccountHandler(@RequestBody User user) throws LoginException{
		
		               String result= loginService.loginIntoAccount(user);
			return new ResponseEntity<>(result,HttpStatus.CREATED);
		
		
		
	}
	
	
@DeleteMapping("/login")
public ResponseEntity<String> logoutFromAccount(@RequestParam String key, @RequestParam String type) throws LoginException{
		
        String result= loginService.logoutFromAccount(key, type);
      return new ResponseEntity<>(result,HttpStatus.CREATED);



}
	
	
	

}
