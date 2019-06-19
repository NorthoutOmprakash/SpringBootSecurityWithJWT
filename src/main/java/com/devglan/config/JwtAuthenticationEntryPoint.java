package com.devglan.config;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.devglan.model.ApiResponse;
import com.google.gson.Gson;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		ApiResponse<JwtAuthenticationFilter> apiResponse = new ApiResponse<>(HttpServletResponse.SC_UNAUTHORIZED,
				authException.getMessage(), null);

		try {
			String json = new Gson().toJson(apiResponse);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.getWriter().write(json);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
		// authException.getMessage());
	}
}