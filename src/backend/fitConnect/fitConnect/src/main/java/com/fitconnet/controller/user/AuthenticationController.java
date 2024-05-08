package com.fitconnet.controller.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.requets.SignUpRequest;
import com.fitconnet.dto.requets.SigninRequest;
import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.dto.response.user.JwtAuthenticationResponse;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.service.interfaces.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Qualifier("authenticationService")
	private final AuthenticationService authenticationService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	public AuthenticationController(AuthenticationService authenticationService,
			GlobalExceptionHandler globalExceptionHandler) {
		this.authenticationService = authenticationService;
		this.globalExceptionHandler = globalExceptionHandler;
	}

	@PostMapping("/signup")
	@Operation(summary = "Registro de usuario", description = "Registro de un nuevo usuario")
	@ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@PostMapping("/signin")
	@Operation(summary = "Inicio de sesión", description = "Inicio de sesión de usuario")
	@ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		return globalExceptionHandler.handleNoHandlerFoundException(ex, request);
	}

}