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

public interface UserServiceI {

	Optional<Set<User>> getAll();

	User getById(Long id);

	Optional<User> getByUserName(String userName);

	Optional<List<User>> getFriends(Long id);

	Optional<Set<Activity>> getCreatedActivities(Long id);

	Optional<Set<Activity>> getInvitedActivities(Long id);

	Optional<Set<Activity>> getAllActivities(Long id);

	Optional<Set<Notification>> getNotifications(Long id);

	List<User> userSetToSortedList();

	UserDetailsService userDetailsService();

	Boolean existById(Long id);

	Boolean existByEmail(String email);

	void create(User user);

	void update(Long id, User user);

	void patch(Long id, User user);

	void deleteById(Long id);

	void userDTOtoUSer(UserDTO request, User user, Role rol);

	void usertoUserDTO(UserDTO response, User user, Role rol);

}
