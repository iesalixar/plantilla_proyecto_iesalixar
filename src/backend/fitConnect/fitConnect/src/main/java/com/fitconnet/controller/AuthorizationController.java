package com.fitconnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/resources")
public class AuthorizationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@GetMapping
	@Operation(summary = "Saludar", description = "Retorna un saludo simple")
	@ApiResponse(responseCode = "200", description = "Saludo obtenido exitosamente")
	public ResponseEntity<String> sayHello() {
		logger.info("## AuthorizationController :: sayHello");
		return ResponseEntity.ok("Here is your resource");
	}
}