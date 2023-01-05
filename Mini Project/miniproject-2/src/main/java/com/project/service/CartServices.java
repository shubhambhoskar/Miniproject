package com.project.service;

import java.util.List;

import com.project.Exception.CartException;
import com.project.Exception.CustomerException;
import com.project.Exception.LoginException;
import com.project.Exception.ProductException;
import com.project.model.Cart;
import com.project.model.Product;



public interface CartServices {
	
	public Cart addProductToCart(Integer productId, int quantity, String key)throws ProductException,LoginException,CartException,CustomerException;
	
	public Cart updateProductQuantity(Integer productId, int quantity, String key)throws ProductException,LoginException,CartException, CustomerException;
	
	public List<Product> viewAllProducts(String key)throws ProductException,LoginException,CartException, CustomerException;
	
	public Cart removeProductFromCart(Integer productId, String key)throws ProductException,LoginException,CartException,CustomerException;
	
	
}
