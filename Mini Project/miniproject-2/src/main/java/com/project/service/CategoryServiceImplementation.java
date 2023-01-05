package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.CategoryDao;
import com.project.Dao.CurrentUserSessionDao;
import com.project.Exception.AdminException;
import com.project.Exception.CategoryException;
import com.project.Exception.LoginException;
import com.project.model.Category;
import com.project.model.CurrentUserSession;


@Service
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CurrentUserSessionDao csdao;

	@Override
	public Category addCategory(Category category, String key)throws CategoryException, AdminException, LoginException {
		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

			return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(Category category, String key)throws CategoryException, AdminException, LoginException {
		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Category> existedCategory = categoryDao.findById(category.getCatId());
		if (existedCategory.isPresent()){
			Category savedCategory = categoryDao.save(category);
			return savedCategory;
		} else {
		throw new CategoryException("Category not exited with this CategoryId" + category.getCatId());
		}
	}

	@Override
	public Category deleteCategory(Integer categoryId, String key)throws CategoryException, AdminException, LoginException {
		CurrentUserSession loggedInUser = csdao.findByUuid(key);
		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Category> category = categoryDao.findById(categoryId);
		if (category.isPresent()) {
			Category existingCategory = category.get();
			categoryDao.deleteById(categoryId);
			return existingCategory;
		} else {
			throw new CategoryException("Category not exited with this CategoryId " + categoryId);
		}
	}

	
}
