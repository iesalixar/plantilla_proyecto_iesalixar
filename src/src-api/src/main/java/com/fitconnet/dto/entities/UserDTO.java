package com.fitconnet.dto.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fitconnet.enums.Role;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a user.
 */
@Data
public class UserDTO {
	/**
	 * The unique identifier for the user.
	 */
	private Long id;
	/**
	 * The first name of the user.
	 */
	private String name;

	/**
	 * The age of the user.
	 */
	private Integer age;

	/**
	 * The email address of the user.
	 */
	private String email;

	/**
	 * The password address of the user.
	 */
	private String password;

	/**
	 * The profile picture of the user.
	 */
	private String image;

	/**
	 * The roles associated with the user.
	 */
	private Set<Role> roles;

	/**
	 * The friends of the user.
	 */
	private List<UserDTO> friends = new ArrayList<>();

	/**
	 * The notifications received by the user.
	 */
	private List<NotificationDTO> notifications = new ArrayList<>();

}