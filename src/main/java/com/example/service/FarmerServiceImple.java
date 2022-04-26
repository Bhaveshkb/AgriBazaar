package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Farmer;
import com.example.repository.FarmerRepository;
@Service
public class FarmerServiceImple implements FarmerService{
	@Autowired
	private FarmerRepository farmerRepo;

	public List<Farmer> getAllProducts(int regId) {
		
		return farmerRepo.findAllById(regId) ;
	}
	
	@Override
	public void deleteProductById(int productId) {
		this.farmerRepo.deleteById(productId);
		
	}
	
	@Override
	public List<Farmer> viewProductById(int productId) {
		
		return this.farmerRepo.getProductBy(productId);
	}
	
	@Override
	public void bidUpdate(Farmer farmer) {
		this.farmerRepo.save(farmer);
		
	}

	@Override
	public Farmer getProductById(int productId) {
	  
	   return this.farmerRepo.getById(productId);
	}

	@Override
	public void saveUpdate(Farmer farmer) {
		this.farmerRepo.save(farmer);
	}

	
}

