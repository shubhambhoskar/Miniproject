package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Address;


public interface AddressDao extends JpaRepository<Address, Integer>{

}
