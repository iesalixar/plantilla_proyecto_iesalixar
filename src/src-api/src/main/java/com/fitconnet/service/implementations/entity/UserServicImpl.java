package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.CommentServiceI;
import com.fitconnet.service.interfaces.entity.NotificationServiceI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServicImpl implements UserServiceI {

	private final UserRepository userRepository;
	private final ActivityServiceI activityService;
	private final NotificationServiceI notificationService;
	private final CommentServiceI commentService;

	@Override
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream().map(this::userToUserDTO).toList();
	}

	@Override
	public UserDTO getById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return userToUserDTO(user);
	}

	@Override
	public UserDTO getByUserName(String userName) {
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getFriends(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getFriends().stream().map(this::userToUserDTO).toList();
	}

	@Override
	public List<ActivityDTO> getCreatedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getCreatedActivities().stream().map(activity -> activityService.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public List<ActivityDTO> getInvitedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getInvitedActivities().stream().map(activity -> activityService.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public List<ActivityDTO> getAllActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		List<Activity> allActivities = new ArrayList<>();
		allActivities.addAll(user.getCreatedActivities());
		allActivities.addAll(user.getInvitedActivities());

		return allActivities.stream().map(activityService::activityToActivityDTO).toList();
	}

	@Override
	public List<NotificationDTO> getNotifications(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getNotifications().stream().map(notificationService::notificationToNotificationDTO).toList();
	}

	@Override
	public List<CommentDTO> getComments(UserDTO user) {
		return user.getComments();
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public void create(UserDTO user) {
		userRepository.save(userDTOtoUser(user));
	}

	@Override
	public void update(Long id, UserDTO user) {

		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		deleteById(id);

		userRepository.save(userDTOtoUser(user));

	}

	@Override
	public void patch(Long id, UserDTO user) {
		User aux = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		updateFieldIfDifferent(aux, user.getFirstName(), "firstName", aux::setFirstName);
		updateFieldIfDifferent(aux, user.getLastName(), "lastName", aux::setLastName);
		updateFieldIfDifferent(aux, user.getUsername(), "userName", aux::setUsername);
		if (!user.getAge().equals(aux.getAge())) {
			aux.setAge(user.getAge());
		}

		updateFieldIfDifferent(aux, user.getEmail(), "email", aux::setEmail);
		updateFieldIfDifferent(aux, user.getPassword(), "password", aux::setPassword);
	}

	@Override
	public void deleteById(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		userRepository.deleteById(id);
	}

	@Override
	public Boolean existById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User userDTOtoUser(UserDTO request) {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUsername(request.getUsername());
		user.setAge(request.getAge());
		user.setEmail(request.getEmail());
		user.setPassword((request.getPassword()));
		user.setRoles(request.getRoles());
		return user;

	}

	@Override
	public UserDTO userToUserDTO(User user) {
		UserDTO response = new UserDTO();
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setUsername(user.getUsername());
		response.setAge(user.getAge());
		response.setEmail(user.getEmail());
		response.setPassword((user.getPassword()));
		response.setRoles(user.getRoles());
		return response;
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
			return user.getUsername();
		case "email":
			return user.getEmail();
		case "password":
			return user.getPassword();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}
