package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
