package com.masai.service;


import java.util.Set;
import com.masai.exceptions.CartException;
import com.masai.model.DemandingVegetableDTO;





public interface CartService {
	
	
	public Set<DemandingVegetableDTO> addToCart(DemandingVegetableDTO demandingVegetableDTO, String key) throws CartException;

	public Set<DemandingVegetableDTO> increaseVegetableQuantity(String name,String type, Integer quantity, String key) throws CartException;
	
	public Set<DemandingVegetableDTO> decreaseVegetableQuantity(String name,String type, Integer quantity, String key) throws CartException;
	
	public Set<DemandingVegetableDTO> removeVegetable(String name,String type, String key) throws CartException;
	
	public String removeAllVegetables(String key) throws CartException;
	
	 public String viewAllVegetables(String key) throws CartException;
	 
	
			
			
		
}
