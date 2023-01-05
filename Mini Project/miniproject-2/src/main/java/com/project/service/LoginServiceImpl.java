package com.project.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.AdminDao;
import com.project.Dao.CurrentUserSessionDao;
import com.project.Dao.CustomerDao;
import com.project.Exception.LoginException;
import com.project.model.Admin;
import com.project.model.AdminDto;
import com.project.model.CurrentUserSession;
import com.project.model.Customer;
import com.project.model.CustomerDto;

import net.bytebuddy.utility.RandomString;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdminDao admindao;

	@Autowired
	private CustomerDao customerRepo;

	@Autowired
	private CurrentUserSessionDao currUserSession;
	
	@Override
	public String loginAdmin(AdminDto admin) throws LoginException {
		Admin existingadmin=admindao.findByAdminUsername(admin.getUsername());
		
		if(existingadmin==null) throw new LoginException("Admin not found");
		
		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingadmin.getAdminId());
		
		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}
		
		if (existingadmin.getPassword().equals(admin.getPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = true;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingadmin.getAdminId(), key, isAdmin,
					LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");
		
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {
		
		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("Admin Not Logged In with this username");
		}

		currUserSession.delete(validCustomerSession);

		return "Admin logged out... !";
	}

	@Override
	public String loginCustomer(CustomerDto user) throws LoginException {
		
		Customer existingCustomer = customerRepo.findByUserName(user.getUserName());

		if (existingCustomer == null)
			throw new LoginException("Invalid credentials. Username does not exist " + user.getUserName());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingCustomer.getCustomerLoginId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingCustomer.getPassword().equals(user.getPassword())) {

			String key = RandomString.make(6);
			
			Boolean isAdmin = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerLoginId(), key, isAdmin,
					LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutCustomer(String key) throws LoginException {
		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Customer Logged Out... !";
	}

}
