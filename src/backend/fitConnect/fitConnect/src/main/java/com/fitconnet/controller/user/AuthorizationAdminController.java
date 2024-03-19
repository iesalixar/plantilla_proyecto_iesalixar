package com.fitconnet.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.dto.UserDTO;
import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/users")
public class AuthorizationAdminController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationAdminController.class);

	private UserServiceI userService;

	public AuthorizationAdminController(UserServiceI userService) {
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> showUsers() {
		logger.info("## AuthorizationAdminController :: showUsers");
		List<UserDTO> userList = userService.getAllUsers();
		return ResponseEntity.ok(userList);
	}
}