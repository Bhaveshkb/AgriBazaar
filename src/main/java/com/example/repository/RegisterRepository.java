package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entiry.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
	
	@Query("select u from Register u where u.username = :username")
	public Register getRegisterByRegisterName(@Param("username") String username) ;
	
//	@Query("select u from Register u from u.password = :password")
//	public Register getRegisterByRegisterPassword(@Param("password") String password);
}
