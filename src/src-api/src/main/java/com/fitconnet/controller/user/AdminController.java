package com.fitconnet.controller.user;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Image;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.ImageServiceI;
import com.fitconnet.service.interfaces.entity.NotificationServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

/**
 * REST Controller for managing admin operations.
 */
@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AdminController {
	/**
	 * Dependency injection for the UserServiceI interface.
	 */
	@Qualifier("userService")
	private final UserServiceI userService;
	/**
	 * Dependency injection for the ActivityServiceI interface.
	 */
	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	/**
	 * Dependency injection for the NotificationServiceI interface.
	 */
	@Qualifier("notificationService")
	private final NotificationServiceI notificationService;
	/**
	 * Dependency injection for the ImageServiceI interface.
	 */
	private final ImageServiceI imageService;
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
	 * Logger instance for AdminController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	/**
	 * Retrieves dashboard data.
	 * 
	 * <p>
	 * Retrieves data for the admin dashboard, including user information and
	 * activities.
	 * </p>
	 * 
	 * @return ResponseEntity<Map<String, Object>> The response entity containing
	 *         the dashboard data.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Show Dashboard", description = "Retrieves data for the admin dashboard, including user information and activities.")
	@ApiResponse(responseCode = "200", description = "Dashboard data retrieved successfully")
	public ResponseEntity<Map<String, Object>> showDashboard() {
		logger.info("## AdminController :: showDashboard");
		List<User> userList = userService.userSetToSortedList();
		Optional<Set<Activity>> activities = activityService.getAll();
		Map<String, Object> dashboardData = new HashMap<>();
		dashboardData.put("users", userList);
		dashboardData.put("activities", activities.orElse(new HashSet<>()));
		return ResponseEntity.ok().body(dashboardData);
	}

	/**
	 * Updates a user.
	 * 
	 * <p>
	 * Updates an existing user with new information.
	 * </p>
	 * 
	 * @param id   The ID of the user to be updated.
	 * @param user The request body containing the updated user information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Update User", description = "Updates an existing user with new information.")
	@ApiResponse(responseCode = "200", description = "User updated successfully")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
		logger.info("## AdminController :: updateUser");
		ResponseEntity<String> response = null;
		boolean exist = userService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.update(id, user);
					return ResponseEntity.ok().body("User updated successfully.");
				});
		return response;
	}

	/**
	 * Updates a notification.
	 * 
	 * <p>
	 * Updates an existing notification with new information.
	 * </p>
	 * 
	 * @param id           The ID of the notification to be updated.
	 * @param notification The request body containing the updated notification
	 *                     information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/notification/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Update Notification", description = "Updates an existing notification with new information.")
	@ApiResponse(responseCode = "200", description = "Notification updated successfully")
	public ResponseEntity<String> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
		logger.info("## AdminController :: updateNotification");
		ResponseEntity<String> response = null;
		Boolean exists = notificationService.existById(id);
		response = processingResponseI.processStringResponse(exists,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.NOTIFICATION_NOT_FOUND), () -> {
					notificationService.update(id, notification);
					return ResponseEntity.ok().body("Notification updated successfully.");
				});
		return response;
	}

	/**
	 * Updates an activity.
	 * 
	 * <p>
	 * Updates an existing activity with new information.
	 * </p>
	 * 
	 * @param id       The ID of the activity to be updated.
	 * @param activity The request body containing the updated activity information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/activity/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Update Activity", description = "Updates an existing activity with new information.")
	@ApiResponse(responseCode = "200", description = "Activity updated successfully")
	public ResponseEntity<String> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
		logger.info("## AdminController :: updateActivity");
		ResponseEntity<String> response = null;
		Boolean exists = activityService.existById(id);
		response = processingResponseI.processStringResponse(exists,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND), () -> {
					activityService.update(id, activity);
					return ResponseEntity.ok().body("Activity updated successfully.");
				});
		return response;
	}

	/**
	 * Updates an image.
	 * 
	 * <p>
	 * Updates an existing image with new information.
	 * </p>
	 * 
	 * @param id    The ID of the image to be updated.
	 * @param image The request body containing the updated image information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/image/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Update Activity by Replacement", description = "Replaces an existing activity with another.")
	@ApiResponse(responseCode = "200", description = "Activity updated successfully")
	public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile file)
			throws IOException, SerialException, SQLException {
		logger.info("ImageController :: updateImage");
		ResponseEntity<String> response = null;
		Boolean exist = imageService.existById(id);
		Image newImage = new Image();
		byte[] bytes = file.getBytes();
		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		newImage.setImage(blob);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.IMAGE_NOT_FOUND), () -> {
					imageService.update(id, newImage);
					return ResponseEntity.ok().body("Image has been deleted successfully.");
				});
		return response;
	}

	/**
	 * Deletes a user.
	 * 
	 * <p>
	 * Deletes the user with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the user to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Delete User", description = "Deletes a user.")
	@ApiResponse(responseCode = "200", description = "User deleted successfully")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.info("## AdminController :: deleteUser");
		ResponseEntity<String> response = null;
		Boolean exist = userService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.deleteById(id);
					return ResponseEntity.ok().body("User has been deleted successfully.");
				});
		return response;
	}

	/**
	 * Deletes an activity.
	 * 
	 * <p>
	 * Deletes the activity with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the activity to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/activity/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Delete Activity", description = "Deletes an activity.")
	@ApiResponse(responseCode = "200", description = "Activity deleted successfully")
	public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
		logger.info("AdminController :: deleteActivity");
		ResponseEntity<String> response = null;
		Boolean exist = activityService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND), () -> {
					activityService.deleteById(id);
					return ResponseEntity.ok().body("Activity has been deleted successfully.");
				});
		return response;
	}

	/**
	 * Deletes an activity.
	 * 
	 * <p>
	 * Deletes the activity with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the activity to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/activity/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Delete Image", description = "Deletes an image.")
	@ApiResponse(responseCode = "200", description = "Image deleted successfully")
	public ResponseEntity<String> deleteImage(@PathVariable Long id) {
		logger.info("AdminController :: deleteImage");
		ResponseEntity<String> response = null;
		Boolean exist = activityService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.IMAGE_NOT_FOUND), () -> {
					imageService.delete(id);
					return ResponseEntity.ok().body("Image has been deleted successfully.");
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
