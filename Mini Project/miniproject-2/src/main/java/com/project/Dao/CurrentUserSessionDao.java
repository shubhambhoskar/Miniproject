package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.CurrentUserSession;

public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>{

	public CurrentUserSession findByUuid(String uuid);

}
