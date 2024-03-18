package com.fitconnet.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fitconnet.dto.UserDTO;
import com.fitconnet.persitence.model.User;

public interface UserServiceI {

	UserDetailsService userDetailsService();

	List<UserDTO> getAllUsers();

	UserDTO createUser(User user);

	UserDTO updateUser(Long id, User user);

	UserDTO patchUser(Long id, UserDTO userDTO);

	UserDTO deleteById(Long id);

	UserDTO getUser(Long id);
}
