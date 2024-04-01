package com.fitconnet.service.interfaces;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.persitence.model.User;

public interface UserServiceI {

	UserDetailsService userDetailsService();

	Set<User> getAllUsers();

	User createUser(User user);

	User updateUser(Long id, User user);

	User patchUser(Long id, User user);

	User deleteById(Long id);

	User getUser(Long id);
}
