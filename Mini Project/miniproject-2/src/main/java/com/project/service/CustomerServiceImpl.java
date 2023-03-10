package com.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.CartDao;
import com.project.Dao.CurrentUserSessionDao;
import com.project.Dao.CustomerDao;
import com.project.Exception.CustomerException;
import com.project.Exception.LoginException;
import com.project.model.Cart;
import com.project.model.CurrentUserSession;
import com.project.model.Customer;
import com.project.model.Product;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CurrentUserSessionDao sessiondao;
	
	@Autowired
	private CartDao cartdao;
	
	@Override
	public Customer saveCustomer(Customer customer) throws CustomerException {
		Customer existingUserName = customerDao.findByUserName(customer.getUserName());

		if (existingUserName != null)
			throw new CustomerException("Username already exists " + customer.getUserName());
		
		Cart cart = new Cart();
		cart.setProducts(new HashMap<Product, Integer>());
		customer.setCart(cart);
		cart.setCustomer(customer);

		return customerDao.save(customer);
	}

	@Override
	public Customer delCustomer(String username) throws CustomerException {
		Customer existingCustomer = customerDao.findByUserName(username);

		if (existingCustomer == null)
			throw new CustomerException("Customer does not exists with this username " + username);

		customerDao.deleteAll();

		return existingCustomer;
	}

	@Override
	public Customer findByUserName(String userName) throws CustomerException {
		
		Customer existingCustomer = customerDao.findByUserName(userName);

		if (existingCustomer != null)
			return existingCustomer;
		else
			throw new CustomerException("Customer does not exists with this userName " + userName);
	}

	@Override
	public List<Customer> findAllCustomer() throws CustomerException {
		List<Customer> customers = customerDao.findAll();

		if (customers.isEmpty())
			throw new CustomerException("No Users Found");

		return customers;
	}

	@Override
	public Customer findByCustomerLoginId(Integer CustomerLoginId) throws CustomerException {
		Optional<Customer> existingCustomer = customerDao.findById(CustomerLoginId);

		if (existingCustomer.isPresent())
			return existingCustomer.get();
		else
			throw new CustomerException("Customer does not exists with this CustomerLoginId " + CustomerLoginId);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException, LoginException {
		CurrentUserSession loggedInUser = sessiondao.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}
		
		if (customer.getCustomerLoginId() == loggedInUser.getUserId()) {
			return customerDao.save(customer);
		} else
			throw new LoginException("Invalid customer Details, please login first");
	}
	

	

}
