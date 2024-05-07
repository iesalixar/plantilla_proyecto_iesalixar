package com.fitconnet.controller.user;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Qualifier("userService")
	private final UserServiceI userService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserServiceI userService) {
		this.userService = userService;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/friends/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<User>> getFriends(@PathVariable Long id) {
		User user = userService.getUserById(id);
		Set<User> friends = user.getFriends();
		return ResponseEntity.ok(friends);
	}

	@GetMapping("/activities/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getActivities(@PathVariable Long id) {
		User user = userService.getUserById(id);
		Set<Activity> activities = userService.getAllActivities(user);
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/activities/created/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getCreatedActivities(@PathVariable Long id) {
		User user = userService.getUserById(id);
		Set<Activity> activities = userService.getCreatedActivities(user);
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/activities/invited/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getInvitedActivities(@PathVariable Long id) {
		User user = userService.getUserById(id);
		Set<Activity> activities = userService.getInvitedActivities(user);
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/notifications/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Notification>> getNotifications(@PathVariable Long id) {
		User user = userService.getUserById(id);
		Set<Notification> notifications = userService.getNotifications(user);
		return ResponseEntity.ok(notifications);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User user) {
		User patchedUser = userService.patchUser(id, user);
		return ResponseEntity.ok(patchedUser);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		User deletedUser = userService.deleteById(id);
		return ResponseEntity.ok(deletedUser);
	}

}
