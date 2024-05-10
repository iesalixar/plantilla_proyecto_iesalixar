package com.fitconnet.service.interfaces.security;

import com.fitconnet.dto.requets.SignUpDTO;
import com.fitconnet.dto.requets.SigninDTO;
import com.fitconnet.dto.response.JwtAuthenticationDTO;

public interface AuthenticationServiceI {

	/** REGISTRO */
	JwtAuthenticationDTO signup(SignUpDTO request);

	/** ACCESO a Token JWT */
	JwtAuthenticationDTO signin(SigninDTO request);
}
