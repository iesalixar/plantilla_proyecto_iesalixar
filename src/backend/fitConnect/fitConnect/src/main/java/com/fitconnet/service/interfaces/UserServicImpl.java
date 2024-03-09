package com.fitconnet.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.productos.dto.response.user.UsuarioResponse;
import com.api.productos.repositories.interfaces.UserRepository;
import com.api.productos.service.interfaces.UserService;

@Service
public class UserServicImpl implements UserService {

	private final UserRepository userRepository;

	public UserServicImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	@Override
	public List<UsuarioResponse> getAllUsers() {
		return userRepository.findAll().stream().map(usuario -> new UsuarioResponse(usuario.getFirstName(),
				usuario.getLastName(), usuario.getEmail(), usuario.getRoles().toString())).toList();
	}

}
