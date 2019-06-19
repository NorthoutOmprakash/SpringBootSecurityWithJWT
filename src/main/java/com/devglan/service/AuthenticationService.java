package com.devglan.service;

import com.devglan.model.AuthToken;
import com.devglan.model.LoginUser;

public interface AuthenticationService {

	public AuthToken registerUser(LoginUser loginUser);
	
	public String logout(String username);
	
}
