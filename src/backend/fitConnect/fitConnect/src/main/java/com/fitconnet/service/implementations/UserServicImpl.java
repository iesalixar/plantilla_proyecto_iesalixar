package com.fitconnet.service.implementations;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.UserServiceI;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

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

		deleteById(id);

		return userRepository.save(user);

	}

	@Override
	public User patchUser(Long id, @Valid User user) {
		User aux = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado", HttpStatus.NOT_FOUND));

		if (user.getFirstName() != aux.getFirstName()) {
			try {
				aux.setFirstName(user.getFirstName());
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("Debe ser una nombre válido.");
			}
		}

		if (user.getLastName() != aux.getLastName()) {
			try {
				aux.setLastName(user.getLastName());
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("Debe ser un apellido valido.");
			}
		}

		if (user.getEmail() != aux.getEmail()) {
			try {
				aux.setEmail(user.getEmail());
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("Debe ser una dirección de correo electrónico válida.");
			}
		}

		if (user.getPassword() != aux.getPassword()) {

			try {
				aux.setPassword(user.getPassword());
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("Debe ser una contraseña válida.");
			}
		}

		return userRepository.save(aux);
	}

	@Override
	public User deleteById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
		userRepository.deleteById(id);
		return user;
	}

	@Override
	public User getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
		return user;
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
		return user;
	}

	@Override
	public Set<User> getFriends(User user) {
		return user.getFriends();
	}

	@Override
	public Set<Activity> getCreatedActivities(User user) {
		return user.getCreatedActivities();
	}

	@Override
	public Set<Activity> getInvitedActivities(User user) {
		return user.getInvitedActivities();
	}

	@Override
	public Set<Activity> getAllActivities(User user) {
		Set<Activity> allActivities = Stream
				.concat(user.getCreatedActivities().stream(), user.getInvitedActivities().stream())
				.collect(Collectors.toSet());
		return allActivities;
	}

	@Override
	public Set<Notification> getNotifications(User user) {
		return user.getNotifications();
	}

}
