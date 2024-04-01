package com.fitconnet.service.implementations;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.UserNotFoundException;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.UserServiceI;

@Service
public class UserServicImpl implements UserServiceI {

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
	public Set<User> getAllUsers() {
		return (Set<User>) userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User user) {

		User findUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

		return userRepository.save(user);
	}

	@Override
	public User patchUser(Long id, User user) {
		User aux = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
		if (aux.getFirstName() != null) {
			aux.setFirstName(user.getFirstName());
		}
		if (aux.getLastName() != null) {
			aux.setLastName(user.getLastName());
		}
		if (aux.getEmail() != null) {
			aux.setEmail(user.getEmail());
		}

		return userRepository.save(aux);
	}

	@Override
	public User deleteById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found", HttpStatus.NOT_FOUND));
		userRepository.deleteById(id);
		return user;
	}

	@Override
	public User getUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found", HttpStatus.NOT_FOUND));
		return user;
	}

}
