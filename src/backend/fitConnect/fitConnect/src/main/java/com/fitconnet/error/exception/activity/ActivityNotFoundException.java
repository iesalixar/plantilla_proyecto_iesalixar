package com.fitconnet.error.exception.activity;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ActivityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	public ActivityNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;

	}

}