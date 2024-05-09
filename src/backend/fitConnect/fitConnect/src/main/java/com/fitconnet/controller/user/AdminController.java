package com.fitconnet.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.ActivityServiceI;
import com.fitconnet.service.interfaces.NotificationServiceI;
import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

	@Qualifier("userService")
	private final UserServiceI userService;
	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	@Qualifier("notificationService")
	private final NotificationServiceI notificationService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	public AdminController(UserServiceI userService, ActivityServiceI activityService,
			NotificationServiceI notificationService, GlobalExceptionHandler globalExceptionHandler) {
		this.userService = userService;
		this.activityService = activityService;
		this.notificationService = notificationService;
		this.globalExceptionHandler = globalExceptionHandler;
	}

//	@GetMapping("/dashboard")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public ResponseEntity<DashboardData> showDashboard() {
//		LOG.info("## AdminController :: showDashboard");
//
//		DashboardData dashboardData = new DashboardData();
//
//		// Obtener todos los usuarios y filtrarlos
//		Set<User> allUsers = userService.getAllUsers();
//		allUsers.removeIf(user -> !user.getRoles().contains("ROLE_USER"));
//		List<User> userList = new ArrayList<>(allUsers);
//		userList.sort(Comparator.comparing(User::getUserName));
//		dashboardData.setUsers(userList);
//
//		// Obtener todas las actividades
//		Set<Activity> allActivities = activityService.getAll();
//		dashboardData.setActivities(allActivities);
//
//		// Obtener todas las notificaciones
//		List<Notification> allNotifications = notificationService.getAllNotifications();
//		dashboardData.setNotifications(allNotifications);
//
//		return ResponseEntity.ok(dashboardData);
//	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> showUsers() {
		LOG.info("## AdminController :: showUsers");
		Set<User> userSet = userService.getAllUsers();
		userSet.removeIf(user -> !user.getRoles().contains("ROLE_USER"));
		List<User> userList = new ArrayList<>(userSet);
		userList.sort(Comparator.comparing(User::getUserName));
		return ResponseEntity.ok(userList);
	}

	@GetMapping("/notifications")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Set<Notification>>> showNotifications() {
		LOG.info("## AdminController :: showNotifications");
		Optional<Set<Notification>> notifications = notificationService.getAll();
		return ResponseEntity.ok(notifications);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		LOG.info("## AdminController :: updateUser");
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		LOG.info("## AdminController :: deleteUser");
		User deletedUser = userService.deleteById(id);
		return ResponseEntity.ok(deletedUser);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}
}
