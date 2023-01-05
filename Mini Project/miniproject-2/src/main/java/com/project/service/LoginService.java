package com.project.service;


import com.project.Exception.LoginException;
import com.project.model.AdminDto;
import com.project.model.CustomerDto;

public interface LoginService {

	
	public String loginAdmin(AdminDto admin) throws LoginException;
	
	public String logoutAdmin(String key) throws LoginException;
	
	public String loginCustomer(CustomerDto user) throws LoginException;
	
	public String logoutCustomer(String key) throws LoginException;
}
