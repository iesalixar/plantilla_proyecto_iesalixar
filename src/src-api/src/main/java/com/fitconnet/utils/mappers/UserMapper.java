package com.fitconnet.utils.mappers;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.persitence.model.User;

/**
 * Mapper class for mapping between User entities and DTOs.
 */
@Component
public class UserMapper {

	/**
	 * Converts a UserDTO object to a User object.
	 *
	 * @param userDTO The UserDTO object to be converted.
	 * @return The corresponding User object.
	 */
	public User userDTOtoUser(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		} else {
			User user = new User();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setAge(userDTO.getAge());
			user.setEmail(userDTO.getEmail());
			user.setPassword(userDTO.getPassword());
			user.setImage(userDTO.getImage());
			user.setRoles(userDTO.getRoles());
			return user;
		}
	}

	/**
	 * Converts a User object to a UserDTO object.
	 *
	 * @param user The User object to be converted.
	 * @return The corresponding UserDTO object.
	 */
	public UserDTO userToUserDTO(User user) {
		if (user == null) {
			return null;
		} else {
			UserDTO response = new UserDTO();
			response.setId(user.getId());
			response.setName(user.getName());
			response.setAge(user.getAge());
			response.setEmail(user.getEmail());
			response.setImage(user.getImage());
			response.setPassword(user.getPassword());
			response.setRoles(user.getRoles());
			return response;
		}
	}

	/**
	 * Converts a list of UserDTO objects to a list of User objects.
	 *
	 * @param dtos The list of UserDTO objects to be converted.
	 * @return The corresponding list of User objects.
	 */
	public List<User> userDTOsToUsers(List<UserDTO> dtos) {
		if (dtos == null) {
			return Collections.emptyList();
		}
		return dtos.stream().map(this::userDTOtoUser).toList();
	}

	/**
	 * Converts a list of User objects to a list of UserDTO objects.
	 *
	 * @param users The list of User objects to be converted.
	 * @return The corresponding list of UserDTO objects.
	 */
	public List<UserDTO> usersToUserDTOs(List<User> users) {
		if (users == null) {
			return Collections.emptyList();
		}
		return users.stream().map(this::userToUserDTO).toList();
	}
}