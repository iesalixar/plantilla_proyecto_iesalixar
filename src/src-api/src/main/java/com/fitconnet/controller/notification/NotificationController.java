package com.fitconnet.controller.notification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.service.interfaces.entity.NotificationServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class NotificationController {
	/**
	 * Dependency injection for the NotificationServiceI interface.
	 */
	@Qualifier("notificationService")
	private final NotificationServiceI notificationService;
	/**
	 * Dependency injection for the UserServiceI interface.
	 */
	@Qualifier("userService")
	private final UserServiceI userService;
	/**
	 * Dependency injection for the ProcessingResponseI interface.
	 */
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;
	/**
	 * Dependency injection for the GlobalExceptionHandler.
	 */
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	/**
	 * Logger instance for ActivityController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	/**
	 * Creates a new notification.
	 * 
	 * <p>
	 * Registers a new notification in the system.
	 * </p>
	 * 
	 * @param notification The request body containing the information of the new
	 *                     notification.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the creation operation.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Create Notification", description = "Registers a new notification in the system.")
	@ApiResponse(responseCode = "200", description = "Notification created successfully")
	public ResponseEntity<String> createNotification(@RequestBody NotificationDTO request) {
		logger.info("NotificationController :: createNotification");
		ResponseEntity<String> response = null;
		boolean exist = notificationService.existByDate(request.getDate());
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The notification already exists"), () -> {
					notificationService.create(request);
					return ResponseEntity.ok().body("Notification created successfully.");
				});
		return response;
	}

	/**
	 * Retrieves all notifications.
	 * 
	 * <p>
	 * Retrieves all notifications registered in the system.
	 * </p>
	 * 
	 * @return ResponseEntity<Optional<Set<Notification>>> The response entity
	 *         containing the set of notifications, if any.
	 */
	@GetMapping()
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get All Notifications", description = "Retrieves all notifications registered in the system.")
	@ApiResponse(responseCode = "200", description = "Notifications retrieved successfully")
	public ResponseEntity<List<NotificationDTO>> getNotifications() {
		logger.info("## NotificationController :: getNotifications");
		List<NotificationDTO> notifications = notificationService.getAll();
		return ResponseEntity.ok().body(notifications);
	}

	/**
	 * Retrieves notifications associated with a specific user.
	 * 
	 * <p>
	 * Retrieves all notifications associated with the specified user.
	 * </p>
	 * 
	 * @param id The ID of the user whose notifications are to be retrieved.
	 * @return ResponseEntity<Optional<Set<Notification>>> The response entity
	 *         containing the set of notifications associated with the user, if any.
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Notifications by User ID", description = "Retrieves notifications associated with a specific user.")
	@ApiResponse(responseCode = "200", description = "Notifications retrieved successfully")
	public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@PathVariable Long id) {
		logger.info("NotificationController :: getNotificationsByUserId");
		ResponseEntity<List<NotificationDTO>> response = null;
		UserDTO user = userService.getById(id);
		response = processingResponseI.processUserResponse(user,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.NOTIFICATION_NOT_FOUND),
				() -> ResponseEntity.ok().body(user.getNotifications()));
		return response;
	}

	/**
	 * Updates a notification.
	 * 
	 * <p>
	 * Updates an existing notification with new information.
	 * </p>
	 * 
	 * @param id      The ID of the notification to be updated.
	 * @param request The request body containing the updated notification
	 *                information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Update Notification", description = "Updates an existing notification with new information.")
	@ApiResponse(responseCode = "200", description = "Notification updated successfully")
	public ResponseEntity<String> patchNotification(@PathVariable Long id, @RequestBody NotificationDTO request) {
		logger.info("NotificationController :: patchNotification");
		ResponseEntity<String> response = null;
		boolean exist = notificationService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.NOTIFICATION_NOT_FOUND), () -> {
					notificationService.patch(id, request);
					return ResponseEntity.ok().body("Notification updated successfully.");
				});
		return response;
	}

	/**
	 * Deletes a notification.
	 * 
	 * <p>
	 * Deletes an existing notification.
	 * </p>
	 * 
	 * @param id The ID of the notification to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Delete Notification", description = "Deletes an existing notification.")
	@ApiResponse(responseCode = "200", description = "Notification deleted successfully")
	public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
		logger.info("ActivityController :: deleteNotification");
		ResponseEntity<String> response = null;
		boolean exist = notificationService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.NOTIFICATION_NOT_FOUND), () -> {
					notificationService.delete(id);
					return ResponseEntity.ok().body("Notification deleted successfully.");
				});
		return response;
	}

	/**
	 * Handles NoHandlerFoundException.
	 * 
	 * <p>
	 * Handles the case when no handler is found for a request.
	 * </p>
	 * 
	 * @param ex      The NoHandlerFoundException instance.
	 * @param request The web request.
	 * @return ResponseEntity<ErrorDetailsResponse> The response entity containing
	 *         details of the error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}
}
