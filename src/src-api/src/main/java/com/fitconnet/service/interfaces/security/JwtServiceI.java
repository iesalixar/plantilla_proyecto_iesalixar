package com.fitconnet.service.interfaces.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface for JWT service.
 */
public interface JwtServiceI {

	/**
	 * Extracts the username from the JWT token.
	 * 
	 * @param token the JWT token
	 * @return the username
	 */
	String extractUserName(String token);

	/**
	 * Generates a JWT token for the UserDetails.
	 * 
	 * @param userDetails the UserDetails
	 * @return the JWT token
	 */
	String generateToken(UserDetails userDetails);

	/**
	 * Checks if a JWT token is valid for the UserDetails.
	 * 
	 * @param token       the JWT token
	 * @param userDetails the UserDetails
	 * @return true if the token is valid, false otherwise
	 */
	boolean isTokenValid(String token, UserDetails userDetails);
}
