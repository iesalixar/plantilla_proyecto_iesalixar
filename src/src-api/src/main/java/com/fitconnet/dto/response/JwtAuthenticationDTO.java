package com.fitconnet.dto.response;

/**
 * Data Transfer Object (DTO) representing JWT authentication token.
 */
public class JwtAuthenticationDTO {
	/**
	 * The JWT authentication token.
	 */
	private String token;

	/**
	 * Constructs a JwtAuthenticationDTO with the provided token.
	 *
	 * @param token The JWT authentication token.
	 */
	public JwtAuthenticationDTO(String token) {
		this.token = token;
	}

	/**
	 * Retrieves the JWT authentication token.
	 *
	 * @return The JWT authentication token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the JWT authentication token.
	 *
	 * @param token The JWT authentication token to set.
	 */
	public void setToken(String token) {
		this.token = token;
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
		 * Builds a JwtAuthenticationDTO instance using the provided token.
		 *
		 * @return JwtAuthenticationDTO instance with the provided token.
		 */
		public JwtAuthenticationDTO build() {
			return new JwtAuthenticationDTO(token);
		}
	}
}
