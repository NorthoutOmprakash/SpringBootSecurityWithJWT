package com.devglan.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devglan.model.AuthToken;

@Repository
public interface AuthTokenDao extends CrudRepository<AuthToken, Integer> {

	AuthToken findByUsername(String username);

	AuthToken findByToken(String token);
	
}
