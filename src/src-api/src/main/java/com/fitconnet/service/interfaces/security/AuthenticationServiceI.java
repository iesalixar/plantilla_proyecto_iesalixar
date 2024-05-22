package com.fitconnet.service.interfaces.security;

import com.fitconnet.dto.requets.SignUp;
import com.fitconnet.dto.requets.Signin;
import com.fitconnet.dto.response.JwtAuthenticationDTO;

/**
 * Interface for Authentication service.
 */
public interface AuthenticationServiceI {

	/**
	 * Registers a new user.
	 * 
	 * @param request the SignUp request
	 * @return the JwtAuthenticationDTO
	 */
	JwtAuthenticationDTO signup(SignUp request);

	/**
	 * Signs in a user and generates a JWT token.
	 * 
	 * @param request the Signin request
	 * @return the JwtAuthenticationDTO
	 */
	JwtAuthenticationDTO signin(Signin request);

}