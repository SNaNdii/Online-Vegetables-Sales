package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.VegetableException;
import com.masai.model.VegetableDTO;

public interface VegetableDTOService {
	
	public VegetableDTO addVegetable(VegetableDTO vegetableDTO, String key) throws VegetableException, CustomerException;
	
	public VegetableDTO updateVegetable(VegetableDTO vegetableDTO ,String key) throws VegetableException, CustomerException;
	
	public VegetableDTO removeVegetable(VegetableDTO vegetableDTO, String key) throws VegetableException, CustomerException;
	
	public List<VegetableDTO> viewAllVegetable(String key) throws VegetableException, CustomerException;
	
	public List<VegetableDTO> viewAllVegetableByCategory(String category, String key) throws VegetableException, CustomerException;
	
	public List<VegetableDTO> viewAllVegetableByName(String name, String key) throws VegetableException, CustomerException;

}
