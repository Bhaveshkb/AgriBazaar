package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="farmer")
public class Farmer {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int productId ;
	
	@Column(name="productname")
	private String productname;
	
	@Column(name="minPrice")
	private double minPrice ;
	
	@Column(name="quantity")
	private int quantity ;
	
	@Column(name="status")
	private String status= "unsold";
	
	@Column(name="highestBid")
	private double highestBid=0.00;
	
//	@Column(name="winnerId")
//	private String winnerId;

	private int reg_id;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "register_id", nullable=false )   // nullable = true
//	@JsonIgnoreProperties("farmer")
//	private Register register ;


	public Farmer() {
		super();
	}


	public Farmer(int productId, String productname, double minPrice, int quantity, @Length(max = 8) String status,
		double highestBid, int reg_id) {
	super();
	this.productId = productId;
	this.productname = productname;
	this.minPrice = minPrice;
	this.quantity = quantity;
	this.status = status;
	this.highestBid = highestBid;
	this.reg_id = reg_id;
}


	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}


	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}

	public int getReg_id() {
		return reg_id;
	}

	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Farmer [productId=" + productId + ", productname=" + productname + ", minPrice=" + minPrice
				+ ", quantity=" + quantity + ", status=" + status + ", highestBid=" + highestBid + ", reg_id=" + reg_id
				+ "]";
	}





}
