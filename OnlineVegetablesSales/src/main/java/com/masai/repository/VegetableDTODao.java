package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.VegetableDTO;

public interface VegetableDTODao extends JpaRepository<VegetableDTO, Integer>{

	public List<VegetableDTO> findByName(String name);
	
	public List<VegetableDTO> findByType(String type);
	
	public VegetableDTO findByNameAndType(String name, String type);
	
}
