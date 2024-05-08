package com.fitconnet.controller.user;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
		User newUser = new User();
		try {
			newUser = userService.createUser(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = new User();
		try {
			user = getUserMethod(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/friends/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<User>> getFriends(@PathVariable Long id) {
		List<User> friends = new ArrayList<>();

		try {
			User user = getUserMethod(id);
			friends = user.getFriends();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(friends);
	}

	@GetMapping("/activities/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getActivities(@PathVariable Long id) {

		Set<Activity> activities = new LinkedHashSet<>();

		try {
			User user = getUserMethod(id);
			activities = userService.getAllActivities(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/activities/created/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getCreatedActivities(@PathVariable Long id) {
		Set<Activity> activities = new LinkedHashSet<>();
		try {
			User user = getUserMethod(id);
			activities = userService.getCreatedActivities(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/activities/invited/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Activity>> getInvitedActivities(@PathVariable Long id) {
		Set<Activity> activities = new LinkedHashSet<>();
		try {
			User user = getUserMethod(id);
			activities = userService.getInvitedActivities(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/notifications/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Set<Notification>> getNotifications(@PathVariable Long id) {
		Set<Notification> notifications = new LinkedHashSet<>();
		try {
			User user = getUserMethod(id);
			notifications = userService.getNotifications(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ResponseEntity.ok(notifications);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User user) {

		User patchedUser = new User();
		try {
			patchedUser = userService.patchUser(id, user);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(patchedUser);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		User deletedUser = new User();
		try {
			deletedUser = userService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(deletedUser);
	}

	private User getUserMethod(Long id) {
		User user = userService.getUserById(id);
		return user;
	}

}
