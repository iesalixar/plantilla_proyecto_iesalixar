package com.fitconnet.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

public interface UserServiceI {

	Optional<Set<User>> getAllUsers();

	Optional<User> getUserById(Long id);

	Optional<User> getUserByUserName(String userName);

	Optional<List<User>> getFriends(Long id);

	Optional<Set<Activity>> getCreatedActivities(Long id);

	Optional<Set<Activity>> getInvitedActivities(Long id);

	Optional<Set<Activity>> getAllActivities(Long id);

	Optional<Set<Notification>> getNotifications(Long id);

	List<User> userSetToSortedList();

	UserDetailsService userDetailsService();

	Boolean existById(Long id);

	Boolean existByEmail(String email);

	void createUser(User user);

	void updateUser(Long id, User user);

	void patchUser(Long id, User user);

	void deleteById(Long id);

	void setUserAttributes(User user, User newUser);

}
