package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exception.CustomerException;
import com.project.Exception.LoginException;
import com.project.model.Customer;
import com.project.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired
	private CustomerService cservice;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> registerUserHandler(@Valid @RequestBody Customer customer) throws CustomerException {

		Customer savedCustomer = cservice.saveCustomer(customer);

		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.OK);

	}
	
	@DeleteMapping("/customers/{username}")
	public ResponseEntity<Customer> deleteUserHandler(@PathVariable("username") String username)throws CustomerException, LoginException {

		Customer updatedCustomer = cservice.delCustomer(username);

		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);

	}
	
	@GetMapping("/customers/{username}")
	public ResponseEntity<Customer> getUserDetailsHandler(@PathVariable String username) throws CustomerException {

		Customer existingCustomer = cservice.findByUserName(username);

		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);

	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllUserDetailsHandler() throws CustomerException {

		List<Customer> CustomerList = cservice.findAllCustomer();

		return new ResponseEntity<List<Customer>>(CustomerList, HttpStatus.OK);

	}

	@PutMapping("/users")
	public ResponseEntity<Customer> updateUserHandler(@Valid @RequestBody Customer customer, @RequestParam("key") String key) throws CustomerException, LoginException {

		Customer updatedCustomer = cservice.updateCustomer(customer, key);

		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);

	}
	
	
	
}
