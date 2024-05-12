package com.fitconnet.service.interfaces.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

/**
 * Interface for User service.
 */
public interface UserServiceI {

	/**
	 * Retrieves all users.
	 * 
	 * @return the optional set of users
	 */
	Optional<Set<User>> getAll();

	/**
	 * Retrieves a user by id.
	 * 
	 * @param id the id of the user to retrieve
	 * @return the user
	 */
	User getById(Long id);

	/**
	 * Retrieves a user by username.
	 * 
	 * @param userName the username of the user to retrieve
	 * @return the optional user
	 */
	Optional<User> getByUserName(String userName);

	/**
	 * Retrieves the friends of a user.
	 * 
	 * @param id the id of the user
	 * @return the optional list of friends
	 */
	Optional<List<User>> getFriends(Long id);

	/**
	 * Retrieves the activities created by a user.
	 * 
	 * @param id the id of the user
	 * @return the optional set of created activities
	 */
	Optional<Set<Activity>> getCreatedActivities(Long id);

	/**
	 * Retrieves the activities invited to by a user.
	 * 
	 * @param id the id of the user
	 * @return the optional set of invited activities
	 */
	Optional<Set<Activity>> getInvitedActivities(Long id);

	/**
	 * Retrieves all activities related to a user.
	 * 
	 * @param id the id of the user
	 * @return the optional set of all activities
	 */
	Optional<Set<Activity>> getAllActivities(Long id);

	/**
	 * Retrieves the notifications of a user.
	 * 
	 * @param id the id of the user
	 * @return the optional set of notifications
	 */
	Optional<Set<Notification>> getNotifications(Long id);

	/**
	 * Converts the user set to a sorted list.
	 * 
	 * @return the sorted list of users
	 */
	List<User> userSetToSortedList();

	/**
	 * Retrieves the user details service.
	 * 
	 * @return the user details service
	 */
	UserDetailsService userDetailsService();

	/**
	 * Checks if a user exists by id.
	 * 
	 * @param id the id of the user
	 * @return true if the user exists, false otherwise
	 */
	Boolean existById(Long id);

	/**
	 * Checks if a user exists by email.
	 * 
	 * @param email the email of the user
	 * @return true if the user exists, false otherwise
	 */
	Boolean existByEmail(String email);

	/**
	 * Creates a new user.
	 * 
	 * @param user the user to create
	 */
	void create(User user);

	/**
	 * Updates a user.
	 * 
	 * @param id   the id of the user to update
	 * @param user the updated user
	 */
	void update(Long id, User user);

	/**
	 * Patches a user.
	 * 
	 * @param id   the id of the user to patch
	 * @param user the patched user
	 */
	void patch(Long id, User user);

	/**
	 * Deletes a user by id.
	 * 
	 * @param id the id of the user to delete
	 */
	void deleteById(Long id);

	/**
	 * Converts a UserDTO to a User.
	 * 
	 * @param request the UserDTO request
	 * @param user    the User to fill
	 * @param rol     the role of the user
	 */
	void userDTOtoUSer(UserDTO request, User user, Role rol);

	/**
	 * Converts a User to a UserDTO.
	 * 
	 * @param response the UserDTO response
	 * @param user     the User to convert
	 * @param rol      the role of the user
	 */
	void usertoUserDTO(UserDTO response, User user, Role rol);
}
