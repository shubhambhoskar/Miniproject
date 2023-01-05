package com.project.service;

import java.util.List;

import com.project.Exception.AdminException;
import com.project.Exception.LoginException;
import com.project.Exception.ProductException;
import com.project.model.Product;



public interface ProductService {
	public Product addProduct(Product product,Integer id, String key) throws ProductException,AdminException, LoginException;

	public Product updateProduct(Product product,Integer id, String key) throws ProductException,AdminException, LoginException;

	public Product deleteProduct(Integer productId,String key) throws ProductException,AdminException, LoginException;
	
	public Product viewProductByid(Integer productId) throws ProductException;
	
	public List<Product> viewProductByCategory(String category) throws ProductException;

	public List<Product> viewAllProduct() throws ProductException;
}
