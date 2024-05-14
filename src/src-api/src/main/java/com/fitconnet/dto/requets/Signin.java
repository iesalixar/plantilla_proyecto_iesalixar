package com.fitconnet.dto.requets;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing sign-in credentials.
 */
@Data
public class Signin {

	/**
	 * The email address or user name used for signing in.
	 */
	private String identifier;

	/**
	 * The password used for signing in.
	 */
	private String password;
}