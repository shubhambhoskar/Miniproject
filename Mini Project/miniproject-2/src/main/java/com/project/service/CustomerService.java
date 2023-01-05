package com.project.service;

import java.util.List;

import com.project.Exception.CustomerException;
import com.project.Exception.LoginException;
import com.project.model.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer) throws CustomerException;

	public Customer delCustomer(String username) throws CustomerException;

	public Customer findByUserName(String userName) throws CustomerException;
	
	public List<Customer> findAllCustomer() throws CustomerException;
	
	public Customer findByCustomerLoginId(Integer CustomerLoginId) throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String key) throws CustomerException, LoginException;
}
