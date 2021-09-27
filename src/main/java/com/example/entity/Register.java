package com.example.entiry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="register")
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	private String firstName ;
	private String lastname ;
	private String mobileno ;
	@Column(unique = true)
	private String username ;
	private String password ;
	private String type ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY,mappedBy = "register")
	private List<Farmer> addProduct = new ArrayList<>();

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Register(int id, String firstName, String lastname, String mobileno, String username, String password,
			String type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.mobileno = mobileno;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Farmer> getAddProduct() {
		return addProduct;
	}

	public void setAddProduct(List<Farmer> addProduct) {
		this.addProduct = addProduct;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", mobileno=" + mobileno
				+ ", username=" + username + ", password=" + password + ", type=" + type + ", addProduct=" + addProduct
				+ "]";
	}
	
	
	
}
