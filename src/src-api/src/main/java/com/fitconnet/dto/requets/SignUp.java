package com.fitconnet.dto.requets;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing sign-up information.
 */
@Data
public class SignUp {

	private String name;
	/**
	 * The age of the user signing up.
	 */
	private Integer age;

	/**
	 * The email address used for signing up.
	 */
	private String email;

	/**
	 * The password chosen by the user for signing up.
	 */
	private String password;

}