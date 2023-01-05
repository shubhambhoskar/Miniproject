package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{


	public Customer findByUserName(String userName);
	
	
}
