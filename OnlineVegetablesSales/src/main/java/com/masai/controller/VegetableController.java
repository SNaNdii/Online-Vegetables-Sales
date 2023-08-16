package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.VegetableException;
import com.masai.model.VegetableDTO;
import com.masai.service.VegetableDTOService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class VegetableController {
	
	@Autowired
	private VegetableDTOService vegetableDTOService;
	
	@PostMapping("/vegetable")
	public ResponseEntity<VegetableDTO> addVegetablehandler(@RequestBody VegetableDTO vegetableDTO, @RequestParam String key) throws VegetableException, CustomerException{
		
		VegetableDTO v = vegetableDTOService.addVegetable(vegetableDTO, key);
		
		return new ResponseEntity<>(v,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/vegetable")
	public ResponseEntity<VegetableDTO> updateVegetable(@RequestBody VegetableDTO vegetableDTO, @RequestParam String key) throws VegetableException, CustomerException{
		
		VegetableDTO v = vegetableDTOService.updateVegetable(vegetableDTO, key);
		
		return new ResponseEntity<>(v,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/vegetable")
	public ResponseEntity<VegetableDTO> removeVegetable(@RequestBody VegetableDTO vegetableDTO, @RequestParam String key) throws VegetableException, CustomerException{
		
		VegetableDTO v = vegetableDTOService.removeVegetable(vegetableDTO, key);
		
		return new ResponseEntity<>(v,HttpStatus.OK);
	}
	
	@GetMapping("/vegetables")
	public ResponseEntity<List<VegetableDTO>> viewAllVegetable(@RequestParam String key) throws VegetableException, CustomerException{
		
		List<VegetableDTO> vs = vegetableDTOService.viewAllVegetable(key);
		
		return new ResponseEntity<>(vs,HttpStatus.OK);
		
	}
	
	@GetMapping("/vegetablesCategory")
	public ResponseEntity<List<VegetableDTO>> viewAllVegetableByCategory(@RequestParam String category, @RequestParam String key) throws VegetableException, CustomerException{
		
		List<VegetableDTO> vs = vegetableDTOService.viewAllVegetableByCategory(category, key);
		
		return new ResponseEntity<>(vs,HttpStatus.OK);
		
	}
	
	@GetMapping("/vegetablesName")
	public ResponseEntity<List<VegetableDTO>> viewAllVegetableByName(@RequestParam String name, @RequestParam String key) throws VegetableException, CustomerException{
		
		List<VegetableDTO> vs = vegetableDTOService.viewAllVegetableByName(name, key);
		
		return new ResponseEntity<>(vs,HttpStatus.OK);
		
	}

}
