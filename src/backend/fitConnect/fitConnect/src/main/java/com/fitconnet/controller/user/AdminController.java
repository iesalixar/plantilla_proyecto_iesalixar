package com.fitconnet.controller.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.ActivityServiceI;
import com.fitconnet.service.interfaces.NotificationServiceI;
import com.fitconnet.service.interfaces.ProcessingResponseI;
import com.fitconnet.service.interfaces.UserServiceI;
import com.fitconnet.utils.Constants;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Qualifier("userService")
	private final UserServiceI userService;
	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	@Qualifier("notificationService")
	private final NotificationServiceI notificationService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public AdminController(UserServiceI userService, ActivityServiceI activityService,
			NotificationServiceI notificationService, GlobalExceptionHandler globalExceptionHandler,
			ProcessingResponseI processingResponseI) {
		this.userService = userService;
		this.activityService = activityService;
		this.notificationService = notificationService;
		this.globalExceptionHandler = globalExceptionHandler;
		this.processingResponseI = processingResponseI;
	}

	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Map<String, Object>> showDashboard() {
		logger.info("## AdminController :: showDashboard");

		List<User> userList = userService.userSetToSortedList();

		Optional<Set<Activity>> activities = activityService.getAllActivity();

		Map<String, Object> dashboardData = new HashMap<>();
		dashboardData.put("users", userList);
		dashboardData.put("activities", activities.orElse(new HashSet<>()));

		return ResponseEntity.ok().body(dashboardData);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> showUsers() {
		logger.info("## AdminController :: showUsers");
		List<User> userList = userService.userSetToSortedList();
		return ResponseEntity.ok().body(userList);
	}

	@GetMapping("/activities")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Set<Activity>>> showActivities() {
		logger.info("## AdminController :: showActivities");
		Optional<Set<Activity>> activities = activityService.getAllActivity();
		return ResponseEntity.ok().body(activities);
	}

	@GetMapping("/notifications")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Set<Notification>>> showNotifications() {
		logger.info("## AdminController :: showNotifications");
		Optional<Set<Notification>> notifications = notificationService.getAll();
		return ResponseEntity.ok().body(notifications);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
		logger.info("## AdminController :: updateUser");
		ResponseEntity<String> response = null;
		Optional<User> existingUser = userService.getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST), () -> {
					userService.updateUser(id, user);
					return ResponseEntity.ok().body("Usuario actualizado");
				});
		return response;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.info("## AdminController :: deleteUser");
		ResponseEntity<String> response = null;
		Optional<User> existingUser = userService.getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST), () -> {
					userService.deleteById(id);
					return ResponseEntity.ok().body("Usuario ha sido eliminado exitosamente");
				});
		return response;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}
}
