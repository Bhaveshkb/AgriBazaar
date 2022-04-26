package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bidding")
public class Bidding {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bidId ;
	
	private int productId ;
	
	private double bidPrice ;
	
	private String BuyerId ;

	public Bidding() {
		super();
	}

	public Bidding(int bidId, int productId, double bidPrice, String buyerId) {
		super();
		this.bidId = bidId;
		this.productId = productId;
		this.bidPrice = bidPrice;
		this.BuyerId = buyerId;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double d) {
		this.bidPrice = d;
	}

	public String getBuyerId() {
		return BuyerId;
	}

	public void setBuyerId(String buyerId) {
		BuyerId = buyerId;
	}

	@Override
	public String toString() {
		return "Bidding [bidId=" + bidId + ", productId=" + productId + ", bidPrice=" + bidPrice + ", BuyerId="
				+ BuyerId + "]";
	}
	
	
	
}
