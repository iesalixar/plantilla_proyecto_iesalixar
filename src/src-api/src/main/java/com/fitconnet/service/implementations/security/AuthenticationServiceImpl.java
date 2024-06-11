package com.fitconnet.service.implementations.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.requets.SignUp;
import com.fitconnet.dto.requets.Signin;
import com.fitconnet.dto.response.JwtAuthenticationDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.security.AuthenticationServiceI;
import com.fitconnet.service.interfaces.security.JwtServiceI;
import com.fitconnet.utils.Mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationServiceI {

	/**
	 * Repository for user-related operations.
	 */
	private final UserRepository userRepository;

	/**
	 * Service for JWT-related operations.
	 */
	private final JwtServiceI jwtService;

	/**
	 * Password encoder.
	 */
	private final PasswordEncoder passwordEncoder;

	/**
	 * Authentication manager.
	 */
	private final AuthenticationManager authenticationManager;

	/**
	 * Logger instance for ActivityController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	private final Mappers userMapper;

	@Override
	public JwtAuthenticationDTO signup(SignUp request) {
		boolean emailExists = userRepository.existsByEmail(request.getEmail());

		if (emailExists) {
			throw new IllegalArgumentException("Email already in use.");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setAge(request.getAge());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.getRoles().add(Role.ROLE_USER);
		userRepository.save(user);

		String jwt = jwtService.generateToken(user);

		UserDTO userDTO = userMapper.userToUserDTO(user);

		return JwtAuthenticationDTO.builder().token(jwt).userDTO(userDTO).build();
	}

	@Override
	public JwtAuthenticationDTO signin(Signin request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = userRepository.findByEmail(request.getIdentifier());
		String jwt = jwtService.generateToken(user);

		UserDTO userDTO = userMapper.userToUserDTO(user);

		return JwtAuthenticationDTO.builder().token(jwt).userDTO(userDTO).build();
	}

}
