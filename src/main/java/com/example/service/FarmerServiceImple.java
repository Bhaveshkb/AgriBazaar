package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entiry.Farmer;
import com.example.repository.FarmerRepository;
@Service
public class FarmerServiceImple implements FarmerService{
	@Autowired
	private FarmerRepository farmerRepo;

	public List<Farmer> getAllProducts() {
		// TODO Auto-generated method stub
		return farmerRepo.findAll() ;
	}
	

}
