package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
