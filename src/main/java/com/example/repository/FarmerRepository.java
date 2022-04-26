package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.example.entity.Farmer;


@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer>  {

	@Query(value="select * from farmer f where f.reg_id=?1",nativeQuery = true)
	List<Farmer> findAllById(int regId);
	
	@Query(value="select * from farmer f where f.product_id=?1", nativeQuery =true)
	List<Farmer> getProductBy(int productId);
	
	@Modifying
	@Transactional
	@Query(value="update farmer u set u.highestBid = :highestBid",nativeQuery = true)
	public void updateHighestBidPrice(double highestBid);
	
	@Query(value="select * from farmer f where f.product_id=?1", nativeQuery =true)
	List<Farmer> getProductById(int productId);
	
}
