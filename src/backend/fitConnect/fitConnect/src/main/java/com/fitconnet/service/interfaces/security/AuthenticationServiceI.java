package com.fitconnet.service.interfaces.security;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.requets.SignUp;
import com.fitconnet.dto.requets.Signin;
import com.fitconnet.dto.response.JwtAuthenticationDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

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

	/**
	 * Creates a new user.
	 * 
	 * @param request the UserDTO request
	 * @param rol     the role of the user
	 * @return the JwtAuthenticationDTO
	 */
	JwtAuthenticationDTO createUser(UserDTO request, Role rol);

	/**
	 * Sets attributes from UserDTO to User.
	 * 
	 * @param request the UserDTO request
	 * @param rol     the role of the user
	 * @return the User
	 */
	User setAttibutesFromDtoToUser(UserDTO request, Role rol);
}