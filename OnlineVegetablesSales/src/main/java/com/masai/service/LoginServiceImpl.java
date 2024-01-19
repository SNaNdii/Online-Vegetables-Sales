package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminUserSession;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.Role;
import com.masai.model.User;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public String loginIntoAccount(User user) throws LoginException {
		
//	 if(user.getRole()==Role.ADMIN)
//	 {
//		
//			Admin existingAdmin= adminDao.findByEmail(user.getEmailId());
//			
//			if(existingAdmin==null)
//			{
//				throw new LoginException("Please Enter a valid email id");
//			}
//			else
//			{
//				Optional<CurrentAdminUserSession> op=adminSessionDao.findById(existingAdmin.getAdminId());
//				
//				if(op.isPresent())
//				{
//					throw new LoginException("you are already Logged In as an admin");
//				}
//				
//				
//					if(existingAdmin.getPassword().equals(user.getPassword()))
//					{
//						CurrentAdminUserSession current=new CurrentAdminUserSession();
//						
//						current.setLocalDateTime(LocalDateTime.now());
//						
//						String key=RandomString.make(6);
//						
//						current.setUuid(key);
//						current.setUserId(existingAdmin.getAdminId());
//						
//						adminSessionDao.save(current);
//						
//						return current.toString();
//						
//					}
//					
//					
//						throw new LoginException("Please enter a valid password");
//				
//				}
//				
//				
//				
//			
//	 }
		
		if (user.getRole() == Role.ADMIN) {
		    String hardcodedAdminEmail = "singhnandita73@gmail.com";
		    String hardcodedAdminPassword = "nandi@123";

		    if (!hardcodedAdminEmail.equals(user.getEmailId())) {
		        throw new LoginException("Please Enter a valid email id");
		    } else {
		        Admin existingAdmin = adminDao.findByEmail(hardcodedAdminEmail);

		        if (existingAdmin == null) {
		            throw new LoginException("Please Enter a valid email id");
		        } else {
		            Optional<CurrentAdminUserSession> op = adminSessionDao.findById(existingAdmin.getAdminId());

		            if (op.isPresent()) {
		                throw new LoginException("You are already Logged In as an admin");
		            }

		            if (hardcodedAdminPassword.equals(user.getPassword())) {
		                CurrentAdminUserSession current = new CurrentAdminUserSession();

		                current.setLocalDateTime(LocalDateTime.now());

		                String key = RandomString.make(6);

		                current.setUuid(key);
		                current.setUserId(existingAdmin.getAdminId());

		                adminSessionDao.save(current);

		                return current.toString();
		            }

		            throw new LoginException("Please enter a valid password");
		        }
		    }
		}
		
		
	 else if(user.getRole()==Role.CUSTOMER)
	 {
		 
		Customer existingCustomer= customerDao.findByEmail(user.getEmailId());
		
		if(existingCustomer==null)
		{
			throw new LoginException("Please Enter a valid email id");
		}
		else
		{
			Optional<CurrentCustomerUserSession> op=customerSessionDao.findById(existingCustomer.getCustomerId());
			
			if(op.isPresent())
			{
				throw new LoginException("you are already Logged In as an customer");
			}
			
			
			
				if(existingCustomer.getPassword().equals(user.getPassword()))
				{
					CurrentCustomerUserSession current=new CurrentCustomerUserSession();
					
					
					
					String key=RandomString.make(6);
					
					current.setLocalDateTime(LocalDateTime.now());
					current.setUuid(key);
					current.setUserId(existingCustomer.getCustomerId());
					
					customerSessionDao.save(current);
					
					return current.toString();
					
				}
				
					throw new LoginException("Please enter a valid password");
				
			}
			
			
			
		 
		 
	 }
	 
	 
	
		 throw new LoginException("Please give the valid type");
	 
		
		
		
		
		
		
	}

	@Override
	public String logoutFromAccount(String key, String type) throws LoginException {
		
		
		if(type.equals("ADMIN"))
		{
			
			CurrentAdminUserSession current=adminSessionDao.findByUuid(key);
			
			if(current!=null)
			{
				
				adminSessionDao.delete(current);
				return "Logged Out!";
				
			}
			
			throw new LoginException("No user is loggedIn with this key");
			
			
		
			
			
		}
		else if(type.equals("CUSTOMER"))
		{
			
			CurrentCustomerUserSession current=customerSessionDao.findByUuid(key);
			
			if(current!=null)
			{
				
				customerSessionDao.delete(current);
				
				
				return "Logged Out!";	
				
			}
			
			throw new LoginException("No user is loggedIn with this key");			
			
			
		}
		
			throw new LoginException("Please enter a valid user type");
		
		
	
		
		
		
	}

}
