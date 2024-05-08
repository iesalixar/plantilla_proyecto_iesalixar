package com.fitconnet.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.NotificationServiceI;
import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Qualifier("userService")
	private final UserServiceI userService;
	private final NotificationServiceI notificationService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public AdminController(UserServiceI userService, NotificationServiceI notificationService) {
		this.userService = userService;
		this.notificationService = notificationService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> showUsers() {
		logger.info("## AuthorizationAdminController :: showUsers");
		Set<User> userSet = userService.getAllUsers();
		userSet.removeIf(user -> !user.getRoles().contains("ROLE_USER"));
		List<User> userList = new ArrayList<>(userSet);
		userList.sort(Comparator.comparing(User::getUserName));
		return ResponseEntity.ok(userList);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Set<Notification>> showNotifications() {
		logger.info("## AuthorizationAdminController :: showNotifications");
		Set<Notification> notifications = notificationService.getAll();
		return ResponseEntity.ok(notifications);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		logger.info("## AuthorizationAdminController :: updateUser");
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		logger.info("## AuthorizationAdminController :: deleteUser");
		User deletedUser = userService.deleteById(id);
		return ResponseEntity.ok(deletedUser);
	}

}
