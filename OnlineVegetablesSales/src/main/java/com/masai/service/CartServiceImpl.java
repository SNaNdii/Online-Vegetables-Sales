package com.masai.service;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CartException;
import com.masai.model.Cart;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.DemandingVegetableDTO;
import com.masai.model.VegetableDTO;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.VegetableDTODao;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private VegetableDTODao vegetableDTODao;
	
	//@Autowired
	//private DemandingVegetableDTODao demandingVegetableDTODao;
	
	@Autowired
	private CartDao cartDao;
	

	@Override
	public Set<DemandingVegetableDTO> addToCart(DemandingVegetableDTO demandingVegetableDTO, String key) throws CartException {
		
		   CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		VegetableDTO v=vegetableDTODao.findByNameAndType(demandingVegetableDTO.getName(), demandingVegetableDTO.getType());
		
		if(v==null)
		{
			throw new CartException("This vegetable does not exist");
		}
		
		
		
		if(demandingVegetableDTO.getQuantity()>v.getQuantity())
		{
			throw new CartException("only "+v.getQuantity()+" stock is available");
		}
		
		demandingVegetableDTO.setPrice(demandingVegetableDTO.getQuantity()*v.getPrice());
		
		
		v.setQuantity(v.getQuantity()-demandingVegetableDTO.getQuantity());
		
		vegetableDTODao.save(v);
		
		    Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart();
		   
		   cart.getDemandingVegetableDto().add(demandingVegetableDTO);
		  
		    cartDao.save(cart);
		   
		 
		    return cart.getDemandingVegetableDto();
		 
		      
	}
	

	@Override
	public Set<DemandingVegetableDTO> increaseVegetableQuantity(String name,String type, Integer quantity, String key) throws CartException {
		
		  CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		   VegetableDTO v=vegetableDTODao.findByNameAndType(name,type);
		   
		   
			if(v==null)
			{
				throw new CartException("This vegetable does not exist in store");
			}
		   
		   
		   
		   Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart(); 
		   
		  Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
		  
		  boolean flag=false;
		  
		  for(DemandingVegetableDTO x:arr)
		  {
			  if(x.getName().equals(name) && x.getType().equals(type))
			  {
				  flag=true;
				  if(quantity>v.getQuantity())
				  {
					  throw new CartException("only "+v.getQuantity()+" stock is available");
				  }
				  else
				  {
					  x.setQuantity(x.getQuantity()+quantity);
					  x.setPrice(x.getPrice()+v.getPrice()*quantity);
					  
					  v.setQuantity(v.getQuantity()-quantity);
				  }
				  break;
			  }
			  
		  }
		  
		  
		  if(flag==false)
		  {
			  throw new CartException("No vegetable is there in your cart with this name and type");
		  }
		  
		  cartDao.save(cart);
		  vegetableDTODao.save(v);
		  
		
	return arr;
		
	}

	@Override
	public Set<DemandingVegetableDTO> decreaseVegetableQuantity(String name,String type, Integer quantity, String key) throws CartException {
		
		
		  CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		   VegetableDTO v=vegetableDTODao.findByNameAndType(name,type);
		   
		   
			if(v==null)
			{
				throw new CartException("This vegetable does not exist in store");
			}
		   
		   
		   
		   Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart(); 
		   
		  Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
		  
		  boolean flag=false;
		  
		  for(DemandingVegetableDTO x:arr)
		  {
			  if(x.getName().equals(name) && x.getType().equals(type))
			  {
				  flag=true;
				  if(quantity<=x.getQuantity())
				  {
					  x.setQuantity(x.getQuantity()-quantity);
					  x.setPrice(x.getPrice()-quantity*v.getPrice());
					  v.setQuantity(v.getQuantity()+quantity);
				  }
				  else
				  {
				
					v.setQuantity(v.getQuantity()+x.getQuantity());
					x.setQuantity(0);
					x.setPrice(0.00);
				  }
				  break;
			  }
			  
		  }
		  
		  
		  if(flag==false)
		  {
			  throw new CartException("No vegetable is there in your cart with this name and type");
		  }
		  
		
		  cartDao.save(cart);
		  vegetableDTODao.save(v);
	return arr;
		
		
	}

	@Override
	public Set<DemandingVegetableDTO> removeVegetable(String name,String type, String key) throws CartException {
	
		
		
		  CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		   
		   
		   Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart(); 
		   
		  Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
		  
		  boolean flag=false;
		  
		  for(DemandingVegetableDTO x:arr)
		  {
			  if(x.getName().equals(name) && x.getType().equals(type))
			  {
				  flag=true;
				   
				  arr.remove(x);
				  break;
			  }
			  
		  }
		  
		  
		  if(flag==false)
		  {
			  throw new CartException("No vegetable is there in your cart with this name and type");
		  }
		  
		  cartDao.save(cart);
	return arr;		
		
		
	}

	@Override
	public String removeAllVegetables(String key) throws CartException {
		
		  CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		   
		   
		   Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart(); 
		   
		  Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
		  
		  if(arr.isEmpty())
		  {
			  throw new CartException("Cart is already empty");  
		  }
		  
	
		  arr.clear();
		  
		 
		  
		  cartDao.save(cart);
	return "Deleted....";	
		
		
	}

	@Override
	public String viewAllVegetables(String key) throws CartException {
		
		  CurrentCustomerUserSession cus=customerSessionDao.findByUuid(key);
		   
		   if(cus==null)
		   {
			   throw new CartException("Please login as an customer first");
		   }
		   
		   
		   
		   Optional<Customer>  op=customerDao.findById(cus.getUserId());
		    
		   Customer customer= op.get();
		   
		   Cart cart=customer.getCart(); 
		   
		  Set<DemandingVegetableDTO> arr=cart.getDemandingVegetableDto();
		  
		  if(arr.isEmpty())
		  {
			  throw new CartException("Cart is already empty");  
		  }
		  
	
		
	return arr.toString();
		
	}
	

}