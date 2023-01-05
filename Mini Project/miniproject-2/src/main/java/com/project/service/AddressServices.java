package com.project.service;

import java.util.List;

import com.project.Exception.AddressException;
import com.project.Exception.CustomerException;
import com.project.Exception.LoginException;
import com.project.model.Address;



public interface AddressServices {
	public Address addAddress(Address address, String key) throws AddressException,CustomerException, LoginException;
	
	public Address updateAddress(Address address, Integer AddressId, String key) throws AddressException,CustomerException, LoginException ;
	
	public Address deleteAddress(Integer AddressId, String key) throws AddressException,CustomerException, LoginException;
	
	public Address viewAddress(Integer AddressId,String key) throws AddressException,CustomerException;
	
	public List<Address> viewAllAddress(String key)throws AddressException,CustomerException;
}
