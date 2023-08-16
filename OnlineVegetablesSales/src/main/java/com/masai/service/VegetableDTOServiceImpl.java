package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.VegetableException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminUserSession;
import com.masai.model.VegetableDTO;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.VegetableDTODao;

@Service
public class VegetableDTOServiceImpl implements VegetableDTOService{
	
	@Autowired
	private VegetableDTODao vegetableDTODao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public VegetableDTO addVegetable(VegetableDTO vegetableDTO, String key) throws VegetableException, CustomerException {
		
		
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(!admin.isPresent()) {
			throw new VegetableException("Please login as admin first");
		}
		  
		
//		VegetableDTO v = vegetableDTODao.findByName(vegetableDTO.getName());
//		
//		if(v!=null) {
//			throw new VegetableException("Vegetable already exists");
//		}
		
		
		return vegetableDTODao.save(vegetableDTO);
	}

	@Override
	public VegetableDTO updateVegetable(VegetableDTO vegetableDTO, String key) throws VegetableException, CustomerException {
		// TODO Auto-generated method stub
		
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(admin==null) {
			throw new VegetableException("Please login as admin first");
		}
		
		
//		VegetableDTO v = vegetableDTODao.findByName(vegetableDTO.getName());
//		
//		if(v==null) {
//			throw new VegetableException("Vegetable doesn't exist");
//		}
		
		return vegetableDTODao.save(vegetableDTO);
	}

	@Override
	public VegetableDTO removeVegetable(VegetableDTO vegetableDTO, String key) throws VegetableException, CustomerException {
		// TODO Auto-generated method stub
		
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(admin==null) {
			throw new VegetableException("Please login as admin first");
		}
		
		List<VegetableDTO> v = vegetableDTODao.findByName(vegetableDTO.getName());
		
		if(v.isEmpty()) {
			throw new VegetableException("Vegetable doesnot exist");
		}
		
		vegetableDTODao.deleteById(vegetableDTO.getVegId());
		
		return vegetableDTO;
	}

	@Override
	public List<VegetableDTO> viewAllVegetable(String key) throws VegetableException, CustomerException {
		// TODO Auto-generated method stub
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(admin==null) {
			throw new VegetableException("Please login as admin first");
		}
		
		List<VegetableDTO> vegetables = new ArrayList<>();
		
		vegetables = vegetableDTODao.findAll();
		
		if(vegetables.isEmpty()) {
			throw new VegetableException("No vegetables in database");
		}
		
		return vegetables;
	}

	@Override
	public List<VegetableDTO> viewAllVegetableByCategory(String category, String key) throws VegetableException, CustomerException {
		// TODO Auto-generated method stub
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(admin==null) {
			throw new VegetableException("Please login as admin first");
		}
		
		List<VegetableDTO> vegetables = new ArrayList<>();
		
		vegetables = vegetableDTODao.findByType(category);
		
		if(vegetables.isEmpty()) {
			throw new VegetableException("No vegetables in database");
		}
		
		return vegetables;
	}

	@Override
	public List<VegetableDTO> viewAllVegetableByName(String name, String key) throws VegetableException, CustomerException {
		// TODO Auto-generated method stub
		
		CurrentAdminUserSession cus= adminSessionDao.findByUuid(key);
		  
		if(cus==null){
			  throw new CustomerException("Please login first");
		}
		
		Optional<Admin> admin = adminDao.findById(cus.getUserId());
		
		if(admin==null) {
			throw new VegetableException("Please login as admin first");
		}
		
		List<VegetableDTO> vegetables = new ArrayList<>();
		
		vegetables = vegetableDTODao.findByName(name);
		
		if(vegetables.isEmpty()) {
			throw new VegetableException("No vegetables in database");
		}
		
		return vegetables;
	}


}
