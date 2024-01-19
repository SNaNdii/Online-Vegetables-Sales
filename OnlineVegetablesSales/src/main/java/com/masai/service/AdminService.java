package com.masai.service;

import com.masai.exceptions.CustomerException;
import com.masai.model.Admin;

public interface AdminService {
	public Admin addAdmin(Admin admin) throws CustomerException;
}
