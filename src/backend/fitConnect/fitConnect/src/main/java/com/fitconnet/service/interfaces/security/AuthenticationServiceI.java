package com.fitconnet.service.interfaces.security;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.requets.SignUp;
import com.fitconnet.dto.requets.Signin;
import com.fitconnet.dto.response.JwtAuthenticationDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

public interface AuthenticationServiceI {

	/** REGISTRO */
	JwtAuthenticationDTO signup(SignUp request);

	/** ACCESO a Token JWT */
	JwtAuthenticationDTO signin(Signin request);

	JwtAuthenticationDTO createUser(UserDTO request, Role rol);

	User setAttibutesFromDtoToUser(UserDTO request, Role rol);
}
