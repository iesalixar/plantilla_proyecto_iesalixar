package com.fitconnet.error.exception.notifications;

import org.springframework.http.HttpStatus;

public class NotificationCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public NotificationCreationException(String message, RuntimeException e, HttpStatus status) {
		super(message);
		this.status = status;

	}

	public HttpStatus getStatus() {
		return status;
	}
}
