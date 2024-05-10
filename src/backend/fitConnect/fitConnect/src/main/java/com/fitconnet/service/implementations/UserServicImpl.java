package com.fitconnet.service.implementations;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fitconnet.enums.Role;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.UserServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
public class UserServicImpl implements UserServiceI {

	private final UserRepository userRepository;

	public UserServicImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<Set<User>> getAll() {
		List<User> usersList = userRepository.findAll();
		Set<User> usersSet = (Set<User>) usersList;
		return Optional.of(usersSet);
	}

	@Override
	public Optional<User> getById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(user);
	}

	@Override
	public Optional<User> getByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(user);
	}

	@Override
	public Optional<List<User>> getFriends(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(user.getFriends());
	}

	@Override
	public Optional<Set<Activity>> getCreatedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(user.getCreatedActivities());
	}

	@Override
	public Optional<Set<Activity>> getInvitedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(user.getInvitedActivities());
	}

	@Override
	public Optional<Set<Activity>> getAllActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		Set<Activity> allActivities = Stream
				.concat(user.getCreatedActivities().stream(), user.getInvitedActivities().stream())
				.collect(Collectors.toSet());
		return Optional.of(allActivities);
	}

	@Override
	public Optional<Set<Notification>> getNotifications(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		return Optional.of(user.getNotifications());
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(Long id, User user) {

		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		deleteById(id);

		userRepository.save(user);

	}

	@Override
	public void patch(Long id, @Valid User user) {
		User aux = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		updateFieldIfDifferent(aux, user.getFirstName(), "firstName", aux::setFirstName);
		updateFieldIfDifferent(aux, user.getLastName(), "lastName", aux::setLastName);
		updateFieldIfDifferent(aux, user.getUserName(), "userName", aux::setUserName);
		updateFieldIfDifferent(aux, user.getEmail(), "email", aux::setEmail);
		updateFieldIfDifferent(aux, user.getPassword(), "password", aux::setPassword);
	}

	private void updateFieldIfDifferent(User user, String newValue, String fieldName, Consumer<String> setter) {
		if (newValue != null && !newValue.equalsIgnoreCase(getFieldValue(user, fieldName))) {
			try {
				setter.accept(newValue);
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("El valor para '" + fieldName + "' no es válido.");
			}
		}
	}

	private String getFieldValue(User user, String fieldName) {
		switch (fieldName) {
		case "firstName":
			return user.getFirstName();
		case "lastName":
			return user.getLastName();
		case "userName":
			return user.getUserName();
		case "email":
			return user.getEmail();
		case "password":
			return user.getPassword();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

	@Override
	public void deleteById(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		userRepository.deleteById(id);
	}

	@Override
	public List<User> userSetToSortedList() {
		Optional<Set<User>> userSet = getAll();
		return userSet.orElse(new HashSet<>()).stream().filter(user -> !user.getRoles().contains(Role.ROLE_ADMIN))
				.sorted(Comparator.comparing(User::getUserName)).toList();
	}

	@Override
	public Boolean existById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public Boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void setAttributes(User user, User newUser) {
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUserName(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());// TODO crear password encoder.
		// Asignar el rol de usuario común
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		newUser.setRoles(roles);

	}

}
