package com.masai.service;

import com.masai.exceptions.LoginException;
import com.masai.model.User;

public interface LoginService {
	
	
	 public String loginIntoAccount(User user) throws LoginException;
	 public String logoutFromAccount(String key, String type) throws LoginException;

}
