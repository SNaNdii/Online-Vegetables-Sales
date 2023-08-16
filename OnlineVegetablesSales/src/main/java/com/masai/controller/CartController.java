package com.masai.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CartException;
import com.masai.model.DemandingVegetableDTO;
import com.masai.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	
	
	@PostMapping("/addToCart")
	public ResponseEntity<Set<DemandingVegetableDTO>> addToCartHandler(@RequestBody DemandingVegetableDTO demandingVegetableDTO,@RequestParam String key) throws CartException{
		
		    Set<DemandingVegetableDTO> arr= cartService.addToCart(demandingVegetableDTO, key);
		    
		    
		    return new ResponseEntity<>(arr,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/increaseVegetableQuantity")
	public ResponseEntity<Set<DemandingVegetableDTO>> increaseVegetableQuantityHandler(@RequestParam String name,@RequestParam String type,@RequestParam Integer quantity,@RequestParam String key) throws CartException{
		
		
		Set<DemandingVegetableDTO> arr=cartService.increaseVegetableQuantity(name, type, quantity, key);
		
		 return new ResponseEntity<>(arr,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/decreaseVegetableQuantity")
	public ResponseEntity<Set<DemandingVegetableDTO>> decreaseVegetableQuantityHandler(@RequestParam String name,@RequestParam String type,@RequestParam Integer quantity,@RequestParam String key) throws CartException{
		
		
		Set<DemandingVegetableDTO> arr=cartService.decreaseVegetableQuantity(name, type, quantity, key);
		
		 return new ResponseEntity<>(arr,HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/removeVegetable")
	public ResponseEntity<Set<DemandingVegetableDTO>> removeVegetableHandler(@RequestParam String name,@RequestParam String type,@RequestParam String key) throws CartException{
		
		
		Set<DemandingVegetableDTO> arr=cartService.removeVegetable(name, type, key);
		
		 return new ResponseEntity<>(arr,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/removeAllVegetables")
	public ResponseEntity<String> removeAllVegetablesHandler(@RequestParam String key) throws CartException{
		
		
		String result=cartService.removeAllVegetables(key);
		
		 return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/viewAllVegetables")
	public ResponseEntity<String> viewAllVegetablesHandler(@RequestParam String key) throws CartException{
		
		
		String result=cartService.viewAllVegetables(key);
		
		 return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
