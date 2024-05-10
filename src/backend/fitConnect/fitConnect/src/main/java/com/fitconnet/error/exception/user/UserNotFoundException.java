package com.fitconnet.error.exception.user;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public UserNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;

	}

}
