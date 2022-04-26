package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Farmer;

@Service
public interface FarmerService {

	List<Farmer> getAllProducts(int regId);
	
	void deleteProductById(int productId);
	
	List<Farmer> viewProductById(int productId);
	
	Farmer getProductById(int productId);

	void bidUpdate(Farmer farmer);

	void saveUpdate(Farmer farmer);

	
}
