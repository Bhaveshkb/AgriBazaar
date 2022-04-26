package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Bidding;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Integer> {
	@Query(value="select max(bid_price) as bid_price from bidding b where b.product_id=?1",nativeQuery=true)
	double findMaxBid(int id);
	
	@Query(value="select buyer_id from bidding b where b.bid_price=?1 and b.product_id=?2",nativeQuery=true)
	String findWinner(double maxBid, int id);
}
