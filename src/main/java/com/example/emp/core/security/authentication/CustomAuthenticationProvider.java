package com.example.emp.core.security.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return false;
	}

}
