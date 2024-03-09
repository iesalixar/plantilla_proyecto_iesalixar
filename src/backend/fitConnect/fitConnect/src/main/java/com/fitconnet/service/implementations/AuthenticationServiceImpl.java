package com.fitconnet.service.implementations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.productos.dto.request.SignUpRequest;
import com.api.productos.dto.request.SigninRequest;
import com.api.productos.dto.response.user.JwtAuthenticationResponse;
import com.api.productos.entities.Role;
import com.api.productos.entities.Usuario;
import com.api.productos.repositories.interfaces.UserRepository;
import com.api.productos.service.interfaces.AuthenticationService;
import com.api.productos.service.interfaces.JwtService;

import lombok.Builder;

@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		boolean emailExists = userRepository.existsByEmail(request.getEmail());
		if (emailExists) {
			throw new IllegalArgumentException("Email already in use.");
		}

		Usuario user = new Usuario();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.getRoles().add(Role.ROLE_USER);
		userRepository.save(user);

		String jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Usuario user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		String jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}
