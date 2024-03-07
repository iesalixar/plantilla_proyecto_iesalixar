package com.daniel.app.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.app.backend.dtos.UserDTO;
import com.daniel.app.backend.services.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

	private final UserServiceI userService;

	public RestUserController(UserServiceI userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
//		UserDTO user = userService.getUserById(id);
//		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
//	@PostMapping("/create")
//	public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
//		userService.saveUser(userDTO);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//
//	@PutMapping("/update")
//	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
//		userService.updateUser(userDTO);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
