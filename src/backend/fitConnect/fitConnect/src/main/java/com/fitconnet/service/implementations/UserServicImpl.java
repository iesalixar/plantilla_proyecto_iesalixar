package com.fitconnet.service.implementations;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.UserDTO;
import com.fitconnet.error.exception.UserNotFoundException;
import com.fitconnet.mappers.UserMapper;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.UserServiceI;

@Service
public class UserServicImpl implements UserServiceI {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServicImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userMapper.toUserDTOList(userRepository.findAll());
	}

	@Override
	public UserDTO createUser(User user) {
		return userMapper.toUserDTO(userRepository.save(user));
	}

	@Override
	public UserDTO updateUser(Long id, User user) {

		User findUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

		User savedUser = userRepository.save(user);

		return userMapper.toUserDTO(savedUser);
	}

	@Override
	public UserDTO patchUser(Long id, UserDTO userDTO) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found", HttpStatus.NOT_FOUND));
		if (user.getFirstName() != null) {
			user.setFirstName(userDTO.getFirstName());
		}
		if (user.getLastName() != null) {
			user.setLastName(userDTO.getLastName());
		}
		if (user.getEmail() != null) {
			user.setEmail(userDTO.getEmail());
		}

		User savedUser = userRepository.save(user);

		return userMapper.toUserDTO(savedUser);
	}

	@Override
	public UserDTO deleteById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found", HttpStatus.NOT_FOUND));
		userRepository.delete(id);

		return userMapper.toUserDTO(user);
	}

	@Override
	public UserDTO getUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found", HttpStatus.NOT_FOUND));
		return userMapper.toUserDTO(user);
	}

}
