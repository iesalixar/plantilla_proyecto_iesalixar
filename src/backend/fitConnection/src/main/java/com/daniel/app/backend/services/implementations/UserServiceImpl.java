package com.daniel.app.backend.services.implementations;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.daniel.app.backend.dtos.UserDTO;
import com.daniel.app.backend.persistence.model.User;
import com.daniel.app.backend.persistence.repositories.UserRepository;
import com.daniel.app.backend.services.interfaces.UserServiceI;

/**
 * Implementación del servicio para la gestión de usuarios.
 */
@Service
public class UserServiceImpl implements UserServiceI {

	/** Repositorio para acceder a los usuarios en la base de datos. */
	private final UserRepository userRepository;

	/** Utilidad para realizar mapeo de modelos. */
	private final ModelMapper modelMapper;

	/**
	 * Constructor de la clase UserServiceImpl.
	 *
	 * @param userRepository Repositorio de usuarios.
	 * @param modelMapper    Utilidad para mapeo de modelos.
	 */
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToUserDTO).toList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(User user) {
		if (userRepository.existsById(user.getIdUser())) {
			userRepository.save(user);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * Convierte una entidad {@link User} a su correspondiente DTO {@link UserDTO}.
	 *
	 * @param user La entidad {@link User} a convertir.
	 * @return La instancia de {@link UserDTO} resultante de la conversión, o null
	 *         si la entidad de entrada es null.
	 */
	public UserDTO convertToUserDTO(User user) {
		return (user != null) ? modelMapper.map(user, UserDTO.class) : null;
	}

	/**
	 * Convierte un DTO {@link UserDTO} a su correspondiente entidad {@link User}.
	 *
	 * @param userDTO El DTO {@link UserDTO} a convertir.
	 * @return La instancia de {@link User} resultante de la conversión.
	 */
	public User convertToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}

	@Override
	public boolean existUser(String userName) {
		return getUserByUserName(userName) != null;
	}
}
