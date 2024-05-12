package com.fitconnet.error.exception.user;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Custom exception class representing a user not found error.
 */
@Data
public class UserNotFoundException extends RuntimeException {
	/**
	 * The version identifier for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * Constructs a UserNotFoundException with the specified message and HTTP
	 * status.
	 *
	 * @param message The detail message.
	 * @param status  The HTTP status associated with the exception.
	 */
	public UserNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
