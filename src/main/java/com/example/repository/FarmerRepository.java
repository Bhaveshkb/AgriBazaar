package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entiry.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer>  {

}
