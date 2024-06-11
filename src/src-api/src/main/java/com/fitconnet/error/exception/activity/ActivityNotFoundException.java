package com.fitconnet.error.exception.activity;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Custom exception class representing an activity not found error.
 */
@Data
public class ActivityNotFoundException extends RuntimeException {

	/**
	 * The version identifier for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * Constructs an ActivityNotFoundException with the specified message and HTTP
	 * status.
	 *
	 * @param message The detail message.
	 * @param status  The HTTP status associated with the exception.
	 */
	public ActivityNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}