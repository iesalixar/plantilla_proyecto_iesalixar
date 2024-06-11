package com.fitconnet.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing error details.
 */
@Data
@AllArgsConstructor
public class ErrorDetailsDTO {
	/**
	 * The timestamp when the error occurred.
	 */
	private Date timestamp;

	/**
	 * The error message.
	 */
	private String message;

	/**
	 * Additional details about the error.
	 */
	private String details;
}