package com.fitconnet.dto.response;

import com.fitconnet.dto.entities.UserDTO;

/**
 * Data Transfer Object (DTO) representing JWT authentication token.
 */
public class JwtAuthenticationDTO {
	/**
	 * The JWT authentication token.
	 */
	private String token;

	private UserDTO userDTO;

	// Constructor con token
	public JwtAuthenticationDTO(String token) {
		this.token = token;
	}

	// Constructor con token y UserDTO
	public JwtAuthenticationDTO(String token, UserDTO userDTO) {
		this.token = token;
		this.userDTO = userDTO;
	}

	// Getter y Setter para el token
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// Getter y Setter para UserDTO
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * Provides a builder for creating JwtAuthenticationDTO instances.
	 *
	 * @return JwtAuthenticationResponseBuilder for building JwtAuthenticationDTO
	 *         instances.
	 */
	public static JwtAuthenticationResponseBuilder builder() {
		return new JwtAuthenticationResponseBuilder();
	}

	/**
	 * Builder class for constructing JwtAuthenticationDTO instances.
	 */
	public static class JwtAuthenticationResponseBuilder {
		private String token;
		private UserDTO userDTO;

		/**
		 * Sets the JWT authentication token in the builder.
		 *
		 * @param token The JWT authentication token.
		 * @return The JwtAuthenticationResponseBuilder instance.
		 */
		public JwtAuthenticationResponseBuilder token(String token) {
			this.token = token;
			return this;
		}

		/**
		 * Sets the UserDTO in the builder.
		 *
		 * @param userDTO The UserDTO to set.
		 * @return The JwtAuthenticationResponseBuilder instance.
		 */
		public JwtAuthenticationResponseBuilder userDTO(UserDTO userDTO) {
			this.userDTO = userDTO;
			return this;
		}

		/**
		 * Builds a JwtAuthenticationDTO instance using the provided token and UserDTO.
		 *
		 * @return JwtAuthenticationDTO instance with the provided token and UserDTO.
		 */
		public JwtAuthenticationDTO build() {
			return new JwtAuthenticationDTO(token, userDTO);
		}
	}
}