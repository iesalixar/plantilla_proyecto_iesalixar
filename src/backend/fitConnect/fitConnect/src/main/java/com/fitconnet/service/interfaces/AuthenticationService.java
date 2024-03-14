package com.fitconnet.service.interfaces;

public interface AuthenticationService {

	/** REGISTRO */
	JwtAuthenticationResponse signup(SignUpRequest request);

	/** ACCESO a Token JWT */
	JwtAuthenticationResponse signin(SigninRequest request);
}
