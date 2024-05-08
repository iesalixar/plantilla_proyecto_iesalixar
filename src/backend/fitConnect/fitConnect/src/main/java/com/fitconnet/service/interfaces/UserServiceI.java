package com.fitconnet.service.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

public interface UserServiceI {

	UserDetailsService userDetailsService();

	Set<User> getAllUsers();

	User createUser(User user);

	User updateUser(Long id, User user);

	User patchUser(Long id, User user);

	User deleteById(Long id);

	User getUserById(Long id);

	User getUserByUserName(String userName);

	List<User> getFriends(Long id);

	Set<Activity> getCreatedActivities(Long id);

	Set<Activity> getInvitedActivities(Long id);

	Set<Activity> getAllActivities(Long id);

	Set<Notification> getNotifications(Long id);

}
