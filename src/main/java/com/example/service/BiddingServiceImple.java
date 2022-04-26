package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bidding;
import com.example.entity.Farmer;
import com.example.repository.BiddingRepository;
import com.example.repository.FarmerRepository;

@Service
@Transactional
public class BiddingServiceImple {

	@Autowired
	private BiddingRepository biddingRepo;

	@Autowired
	private FarmerRepository farmerRepo;

	public List<Farmer> getAllProducts() {
		return farmerRepo.findAll();
	}

	public String addBid(Bidding bidding) {

		biddingRepo.save(bidding);
		return "Success..";
	}

	public Bidding saveUpdate(Bidding bidding) {
		return this.biddingRepo.save(bidding);
	}

	public double findMaxBid(int id) {

		return this.biddingRepo.findMaxBid(id);
	}

	public String findWinner(double maxBid, int id) {
		return this.biddingRepo.findWinner(maxBid, id);
	}



}
