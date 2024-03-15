package com.fitconnet.error.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public UserNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;

	}

	public HttpStatus getStatus() {
		return status;
	}
}
