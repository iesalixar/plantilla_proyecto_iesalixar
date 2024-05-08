package com.fitconnet.error.exception.notifications;

import org.springframework.http.HttpStatus;

public class NotificationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public NotificationNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;

	}

	public HttpStatus getStatus() {
		return status;
	}

}
