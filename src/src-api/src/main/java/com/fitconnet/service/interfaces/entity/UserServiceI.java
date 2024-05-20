package com.fitconnet.service.interfaces.entity;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.persitence.model.User;

/**
 * Interface for User service.
 */
public interface UserServiceI {

	/**
	 * Retrieves all users.
	 * 
	 * @return A list of all users.
	 */
	List<UserDTO> getAll();

	/**
	 * Retrieves a user by id.
	 * 
	 * @param id The id of the user to retrieve.
	 * @return The user with the specified id, if found.
	 */
	UserDTO getById(Long id);

	/**
	 * Retrieves a user by username.
	 * 
	 * @param userName The username of the user to retrieve.
	 * @return The user with the specified username, if found.
	 */
	UserDTO getByUserName(String userName);

	/**
	 * Retrieves the friends of a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of friends of the specified user.
	 */
	List<UserDTO> getFriends(Long id);

	/**
	 * Converts participant DTOs to participant entities.
	 *
	 * @param participantsDTO The list of participant DTOs to convert.
	 * @return The list of participant entities corresponding to the participant
	 *         DTOs.
	 */
	List<User> participantsDTOtoParticipants(List<UserDTO> participantsDTO);

	/**
	 * Converts participant entities to participant DTOs.
	 *
	 * @param participants The list of participant entities to convert.
	 * @return The list of participant DTOs corresponding to the participant
	 *         entities.
	 */
	List<UserDTO> participantstoParticipantsDTO(List<User> participants);

	/**
	 * Retrieves the user details service.
	 * 
	 * @return The user details service.
	 */
	UserDetailsService userDetailsService();

	/**
	 * Checks if a user exists by id.
	 * 
	 * @param id The id of the user.
	 * @return true if the user exists, false otherwise.
	 */
	Boolean existById(Long id);

	/**
	 * Checks if a user exists by email.
	 * 
	 * @param email The email of the user.
	 * @return true if the user exists, false otherwise.
	 */
	boolean existByEmail(String email);

	/**
	 * Creates a new user.
	 * 
	 * @param user The user to create.
	 */
	void create(UserDTO user);

	/**
	 * Updates a user.
	 * 
	 * @param id   The id of the user to update.
	 * @param user The updated user.
	 */
	void update(Long id, UserDTO user);

	/**
	 * Patches a user.
	 * 
	 * @param id   The id of the user to patch.
	 * @param user The patched user.
	 */
	void patch(Long id, UserDTO user);

	/**
	 * Deletes a user by id.
	 * 
	 * @param id The id of the user to delete.
	 */
	void deleteById(Long id);

	/**
	 * Converts a UserDTO to a User.
	 * 
	 * @param request The UserDTO request.
	 * @param user    The User to fill.
	 * @param rol     The role of the user.
	 */
	User userDTOtoUser(UserDTO request);

	/**
	 * Converts a User to a UserDTO.
	 * 
	 * @param response The UserDTO response.
	 * @param user     The User to convert.
	 * @param rol      The role of the user.
	 */
	UserDTO userToUserDTO(User user);
}
