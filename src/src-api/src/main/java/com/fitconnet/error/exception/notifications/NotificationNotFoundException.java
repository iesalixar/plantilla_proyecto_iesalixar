package com.fitconnet.error.exception.notifications;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Custom exception class representing a notification not found error.
 */
@Data
public class NotificationNotFoundException extends RuntimeException {

	/**
	 * The version identifier for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * Constructs a NotificationNotFoundException with the specified message and
	 * HTTP status.
	 *
	 * @param message The detail message.
	 * @param status  The HTTP status associated with the exception.
	 */
	public NotificationNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}