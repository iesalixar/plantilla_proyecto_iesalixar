package com.fitconnet.service.interfaces;

import com.fitconnet.dto.requets.SignUpRequest;
import com.fitconnet.dto.requets.SigninRequest;
import com.fitconnet.dto.response.user.JwtAuthenticationResponse;

public interface AuthenticationService {

	/** REGISTRO */
	JwtAuthenticationResponse signup(SignUpRequest request);

	/** ACCESO a Token JWT */
	JwtAuthenticationResponse signin(SigninRequest request);
}
