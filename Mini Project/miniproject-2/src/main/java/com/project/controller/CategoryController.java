package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exception.AdminException;
import com.project.Exception.CategoryException;
import com.project.Exception.LoginException;
import com.project.model.Category;
import com.project.service.CategoryService;



@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category category, @RequestParam String key)throws CategoryException, AdminException, LoginException{

		Category newCategory = categoryService.addCategory(category, key);

		return new ResponseEntity<Category>(newCategory, HttpStatus.OK);

	}

	@PutMapping("/category")
	public ResponseEntity<Category> updateCategoryHandler(@Valid @RequestBody Category category, @RequestParam String key)throws CategoryException, AdminException, LoginException{
		Category updatedCategory = categoryService.updateCategory(category,key);

		return new ResponseEntity<Category>(updatedCategory, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategoryHandler(@PathVariable("id") Integer id, @RequestParam String key)throws CategoryException, AdminException, LoginException{

		Category deletedCategory = categoryService.deleteCategory(id,key);

		return new ResponseEntity<Category>(deletedCategory, HttpStatus.OK);
	}
}
