package com.fitconnet.service.interfaces;

import com.api.productos.dto.request.SignUpRequest;
import com.api.productos.dto.request.SigninRequest;
import com.api.productos.dto.response.user.JwtAuthenticationResponse;

public interface AuthenticationService {

	/** REGISTRO */
	JwtAuthenticationResponse signup(SignUpRequest request);

	/** ACCESO a Token JWT */
	JwtAuthenticationResponse signin(SigninRequest request);
}
