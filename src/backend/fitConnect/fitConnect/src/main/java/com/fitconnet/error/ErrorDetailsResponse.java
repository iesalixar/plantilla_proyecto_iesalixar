package com.fitconnet.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailsResponse {
	private Date timestamp;
	private String message;
	private String details;

}
