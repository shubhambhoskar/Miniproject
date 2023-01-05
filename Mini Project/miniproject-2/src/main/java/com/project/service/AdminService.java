package com.project.service;

import java.util.List;

import com.project.Exception.AdminException;
import com.project.Exception.LoginException;
import com.project.model.Admin;

public interface AdminService {

	public Admin saveUser(Admin user) throws AdminException;

	public Admin deleteUser(String adminUsername) throws AdminException;

	public Admin findByAdminId(Integer adminId) throws AdminException;

	public Admin findByUserName(String adminUserName) throws AdminException;

	public List<Admin> findAllUsers() throws AdminException;
	
	public Admin updateUser(Admin user, String key) throws AdminException,LoginException;

}
