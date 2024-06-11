package com.fitconnet.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.requets.SignUp;
import com.fitconnet.dto.requets.Signin;
import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.dto.response.JwtAuthenticationDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.service.interfaces.security.AuthenticationServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AuthenticationController {

	/**
	 * The AuthenticationService instance.
	 */
	@Qualifier("authenticationService")
	private final AuthenticationServiceI authenticationService;
	/**
	 * The GlobalExceptionHandler instance.
	 */
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	/**
	 * Logger instance for ActivityController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	/**
	 * User registration.
	 * 
	 * <p>
	 * Registers a new user.
	 * </p>
	 * 
	 * @param request The request body containing the user's signup information.
	 * @return ResponseEntity<JwtAuthenticationDTO> The response entity containing
	 *         authentication information upon successful registration.
	 */
	@PostMapping("/signup")
	@Operation(summary = "User Registration", description = "Registers a new user.")
	@ApiResponse(responseCode = "200", description = "User registered successfully")
	public ResponseEntity<?> signup(@RequestBody SignUp request) {
		logger.info("AuthenticationController :: signup");
		try {
			JwtAuthenticationDTO authenticationDTO = authenticationService.signup(request);
			return ResponseEntity.ok(authenticationDTO);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
		}
	}

	/**
	 * User sign-in.
	 * 
	 * <p>
	 * Logs in an existing user.
	 * </p>
	 * 
	 * @param request The request body containing the user's signin information.
	 * @return ResponseEntity<JwtAuthenticationDTO> The response entity containing
	 *         authentication information upon successful signin.
	 */
	@PostMapping("/signin")
	@Operation(summary = "User Sign-in", description = "Logs in an existing user.")
	@ApiResponse(responseCode = "200", description = "Sign-in successful")
	public ResponseEntity<?> signin(@RequestBody Signin request) {
		logger.info("AuthenticationController :: signin");
		try {
			JwtAuthenticationDTO authenticationDTO = authenticationService.signin(request);
			return ResponseEntity.ok(authenticationDTO);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
		}
	}

	/**
	 * Handles NoHandlerFoundException.
	 * 
	 * <p>
	 * Handles the case when no handler is found for a request.
	 * </p>
	 * 
	 * @param ex      The NoHandlerFoundException instance.
	 * @param request The web request.
	 * @return ResponseEntity<ErrorDetailsResponse> The response entity containing
	 *         details of the error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		return globalExceptionHandler.handleNoHandlerFoundException(ex, request);
	}

}