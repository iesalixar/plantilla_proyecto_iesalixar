package com.fitconnet.error.exception.notifications;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Custom exception class representing an error during notification creation.
 */
@Data
public class NotificationCreationException extends RuntimeException {

	/**
	 * The version identifier for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * Constructs a NotificationCreationException with the specified message,
	 * underlying cause, and HTTP status.
	 *
	 * @param message The detail message.
	 * @param e       The underlying cause of the exception.
	 * @param status  The HTTP status associated with the exception.
	 */
	public NotificationCreationException(String message, RuntimeException e, HttpStatus status) {
		super(message, e);
		this.status = status;
	}
}
