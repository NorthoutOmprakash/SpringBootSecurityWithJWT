package com.devglan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devglan.model.AuthToken;
import com.devglan.model.LoginUser;
import com.devglan.service.AuthenticationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(value = "/generate-token")
	public ResponseEntity<AuthToken> register(@RequestBody LoginUser loginUser) {

		AuthToken authToken = authenticationService.registerUser(loginUser);

		return new ResponseEntity<>(authToken, HttpStatus.OK);
	}

	@PostMapping(value = "/logout/{username}")
	public ResponseEntity<String> logout(@PathVariable String username) {

		String authToken = authenticationService.logout(username);

		return new ResponseEntity<>(authToken, HttpStatus.OK);

	}

}
