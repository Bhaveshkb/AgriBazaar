package com.example.entity;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="register")
public class Register {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;

	@NotBlank(message="First Name is required")
	@Length(min=3,max=15,message="Invalid Name Length")
	@Column(name="firstName")
	private String firstName ;
	
	@NotBlank(message="Last Name is required")
	@Length(min=3,max=15,message="Invalid Name Length")
	@Column(name="lastname")
	private String lastname ;
	
	@NotBlank(message="Mobile No is required")
	@Length(min=10,max=10,message="Invalid Mobile Number")
	@Column(name="mobileno",unique=true)
	private String mobileno ;
	
	@Email
	@NotBlank(message="UserName is required")
	@Column(name="username",unique = true)
	private String username ;
	
	@NotBlank(message="Password is required")
	@Length(min=8,message="Invalid Password Length")
	@Column(name="password")
	private String password ;
	
	@NotNull
	@Column(name="type")
	private String type ;
	
	public Register() {
		super();
		
	}

	public Register(int id) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "Register [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", mobileno=" + mobileno
				+ ", username=" + username + ", password=" + password + ", type=" + type + "]";
	}
		
}
