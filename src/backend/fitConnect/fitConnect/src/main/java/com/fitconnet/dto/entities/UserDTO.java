package com.fitconnet.dto.entities;

import java.util.Set;

import com.fitconnet.enums.Role;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a user.
 */
@Data
public class UserDTO {
	/**
	 * The first name of the user.
	 */
	private String firstName;

	/**
	 * The last name of the user.
	 */
	private String lastName;

	/**
	 * The username of the user.
	 */
	private String userName;

	/**
	 * The age of the user.
	 */
	private Integer age;

	/**
	 * The email address of the user.
	 */
	private String email;

	/**
	 * The password of the user.
	 */
	private String password;

	/**
	 * The roles associated with the user.
	 */
	private Set<Role> roles;
}