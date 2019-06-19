package com.devglan.service.impl;

import static com.devglan.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.devglan.model.Constants.HEADER_STRING;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.devglan.config.JwtTokenUtil;
import com.devglan.dao.AuthTokenDao;
import com.devglan.model.AuthToken;
import com.devglan.model.LoginUser;
import com.devglan.model.User;
import com.devglan.service.AuthenticationService;
import com.devglan.service.UserService;
import com.google.gson.Gson;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenDao authTokenDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Override
	public AuthToken registerUser(LoginUser loginUser) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final User user = userService.findOne(loginUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);

		AuthToken authToken = authTokenDao.findByUsername(user.getUsername());
		if (authToken != null) {
			authToken.setToken(token);
			authToken.setUsername(user.getUsername());
			authToken.setCreatedTime(new Date(System.currentTimeMillis()));
			authToken.setExpireTime(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000));
			authToken.setIsExpired(false);
			authTokenDao.save(authToken);
		} else {
			authToken = new AuthToken();
			authToken.setToken(token);
			authToken.setUsername(user.getUsername());
			authToken.setCreatedTime(new Date(System.currentTimeMillis()));
			authToken.setExpireTime(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000));
			authToken.setIsExpired(false);
			authTokenDao.save(authToken);
		}
		return authToken;
	}

	@Override
	public String logout(String username) {
		System.out.println("Start of logout() service method ");

		AuthToken authToken = authTokenDao.findByUsername(username);
		authToken.setToken(null);
		authToken.setIsExpired(true);

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		httpServletRequest.setAttribute(HEADER_STRING, authToken.getUsername());

		authTokenDao.save(authToken);

		Map<String, String> logoutSuccess = new HashMap<>();
		logoutSuccess.put("Status", "logout Successfully");

		Gson gson = new Gson();
		String logoutSuccessMessageJson = gson.toJson(logoutSuccess);

		System.out.println("End of logout() service method ");

		return logoutSuccessMessageJson;
	}

}
