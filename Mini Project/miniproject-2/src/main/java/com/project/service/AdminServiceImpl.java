package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.AdminDao;
import com.project.Dao.CurrentUserSessionDao;
import com.project.Exception.AdminException;
import com.project.Exception.LoginException;
import com.project.model.Admin;
import com.project.model.CurrentUserSession;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao dao;

	@Autowired
	private CurrentUserSessionDao sessiondao;
	
	@Override
	public Admin saveUser(Admin user) throws AdminException {
		Admin existuser=dao.findByAdminUsername(user.getAdminUsername());
		if(existuser!=null) throw new AdminException("User already present in system");
		return dao.save(user);
	}

	@Override
	public Admin deleteUser(String adminUsername) throws AdminException {
		Admin admin=dao.findByAdminUsername(adminUsername);
		
		if(admin==null) throw new AdminException("No user found");
		dao.deleteById(admin.getAdminId());
		
		return admin;
	}

	@Override
	public Admin findByAdminId(Integer adminId) throws AdminException {
		Optional<Admin> admin	=dao.findById(adminId);
	
		if(admin.isPresent()) return admin.get();
		else throw new AdminException("Admin not found");
	}

	@Override
	public Admin findByUserName(String adminUserName) throws AdminException {
		Admin a=dao.findByAdminUsername(adminUserName);
		
		if(a!=null)return a;
		else throw new AdminException("Admin not found");
	
	}

	@Override
	public List<Admin> findAllUsers() throws AdminException {
		List<Admin>list=dao.findAll();
		
		if(list.size()==0)throw new AdminException("No admin present in system");
		else return list;
	}

	@Override
	public Admin updateUser(Admin user, String key) throws AdminException, LoginException {
		CurrentUserSession existuser=sessiondao.findByUuid(key);
		
		if(existuser==null) throw new LoginException("User not found");
		
		if(user.getAdminId()==existuser.getUserId()) {
			return dao.save(user);
		}else {
			throw new AdminException("Invalid admin details");
		}
	}




	
	
	
	
}
