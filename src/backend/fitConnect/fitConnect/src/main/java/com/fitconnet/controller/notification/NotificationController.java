package com.fitconnet.controller.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.controller.user.UserController;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.NotificationServiceI;
import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

	@Qualifier("notificationService")
	private final NotificationServiceI notificationService;
	@Qualifier("userService")
	private final UserServiceI userService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	public NotificationController(NotificationServiceI notificationService, UserServiceI userService,
			GlobalExceptionHandler globalExceptionHandler) {
		this.notificationService = notificationService;
		this.userService = userService;
		this.globalExceptionHandler = globalExceptionHandler;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Page<Notification>> getNotificationsById(@PathVariable Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		LOG.info("NotificationController :: getNotificationsById");

		User user = getUserMethod(userId);
		Pageable pageable = PageRequest.of(page, size);
		Page<Notification> notificationsPage = notificationService.getByRecipient(userId, pageable);

		return ResponseEntity.ok(notificationsPage);
	}

	private User getUserMethod(Long id) {
		User user = userService.getUserById(id);
		return user;
	}

}
