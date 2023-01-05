package com.project.service;

import com.project.Exception.AdminException;
import com.project.Exception.CategoryException;
import com.project.Exception.LoginException;
import com.project.model.Category;

public interface CategoryService {
	
	public Category addCategory(Category category, String key) throws CategoryException, AdminException, LoginException;

	public Category updateCategory(Category category, String key) throws CategoryException, AdminException, LoginException;

	public Category deleteCategory(Integer categoryId, String key) throws CategoryException, AdminException, LoginException;
	
}
