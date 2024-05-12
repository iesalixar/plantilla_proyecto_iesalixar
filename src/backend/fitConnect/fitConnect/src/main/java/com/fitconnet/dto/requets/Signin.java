package com.fitconnet.dto.requets;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing sign-in credentials.
 */
@Data
public class Signin {
	/**
	 * The email address used for signing in.
	 */
	private String email;

	/**
	 * The password used for signing in.
	 */
	private String password;
}