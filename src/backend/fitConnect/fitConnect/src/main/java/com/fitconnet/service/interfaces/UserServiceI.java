package com.fitconnet.service.interfaces;

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

	Set<User> getFriends(User user);

	Set<Activity> getCreatedActivities(User user);

	Set<Activity> getInvitedActivities(User user);

	Set<Activity> getAllActivities(User user);

	Set<Notification> getNotifications(User user);

}
