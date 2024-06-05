package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.response.JwtAuthenticationDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.service.interfaces.security.JwtServiceI;
import com.fitconnet.utils.Constants;
import com.fitconnet.utils.mappers.UserMapper;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceI {
	/**
	 * Repository for user-related operations.
	 */
	private final UserRepository userRepository;

	/**
	 * Mapper for mapping between user entities and DTOs.
	 */
	private final UserMapper userMapper;

	/**
	 * Password encoder.
	 */
	private final PasswordEncoder passwordEncoder;

	/**
	 * Service for JWT-related operations.
	 */
	private final JwtServiceI jwtService;

	@Override
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
	}

	@Override
	public UserDTO getById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return userMapper.userToUserDTO(user);
	}

	@Override
	public UserDTO getByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return userMapper.userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getFriends(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getFriends().stream().map(userMapper::userToUserDTO).toList();
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public JwtAuthenticationDTO create(UserDTO user) {
		boolean emailExists = userRepository.existsByEmail(user.getEmail());
		if (emailExists) {
			throw new IllegalArgumentException("Email already in use.");

		}
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		user.setRoles(roles);
		userRepository.save(userMapper.userDTOtoUser(user));
		String jwt = jwtService.generateToken(userMapper.userDTOtoUser(user));
		return JwtAuthenticationDTO.builder().token(jwt).build();

	}

	@Override
	public void update(Long id, UserDTO user) {

		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		deleteById(id);

		userRepository.save(userMapper.userDTOtoUser(user));

	}

	@Override
	public void patch(Long id, UserDTO user) {
		User aux = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		updateFieldIfDifferent(aux, user.getFirstName(), "firstName", aux::setFirstName);
		updateFieldIfDifferent(aux, user.getLastName(), "lastName", aux::setLastName);
		updateFieldIfDifferent(aux, user.getUserName(), "userName", aux::setUserName);
		updateFieldIfDifferent(aux, user.getEmail(), "email", aux::setEmail);
		updateFieldIfDifferent(aux, user.getImage(), "image", aux::setImage);
		if (!user.getAge().equals(aux.getAge())) {
			aux.setAge(user.getAge());
		}
		if (!passwordEncoder.matches(user.getPassword(), aux.getPassword())) {
			aux.setPassword(passwordEncoder.encode(user.getPassword()));
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
	public Boolean existById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
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
		case "image":
			return user.getImage();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}
