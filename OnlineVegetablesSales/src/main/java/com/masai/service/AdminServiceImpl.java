package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.masai.exceptions.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.VegetableDTODao;

public class AdminServiceImpl implements AdminService{

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerSessionDao customerSessionDao;
	@Autowired
	private AdminSessionDao adminSessionDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private VegetableDTODao vegDao;
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin addAdmin(Admin admin) throws CustomerException {

		Admin a =adminDao.findByEmail(admin.getEmail());
		if(a!=null)
		{
			throw new CustomerException("Admin already Exist with this email");
		}
		
		if(admin.getPassword().equals(admin.getConfirmPassword()))
		{
			
			
			Admin new_admin=adminDao.save(admin);
			
//			Cart cr=new Cart();
//			cr.setAdmin(new_admin);
//			new_admin.setCart(cr);
//			
//			cartDao.save(cr);
			
			return new_admin;
		}
		else
		{
			throw new CustomerException("Password and Confirm Password did not matched");
		}
		
	}

}
