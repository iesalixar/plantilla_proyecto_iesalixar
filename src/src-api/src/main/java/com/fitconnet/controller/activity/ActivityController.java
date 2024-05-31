package com.fitconnet.controller.activity;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/activity")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ActivityController {

	/**
	 * Dependency injection for the ActivityService interface.
	 */
	@Qualifier("activityService")
	private final ActivityServiceI activityService;

	/**
	 * Dependency injection for the UserService interface.
	 */
	@Qualifier("userService")
	private final UserServiceI userService;

	/**
	 * Dependency injection for the ProcessingResponse interface.
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
	private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	/**
	 * Creates a new activity.
	 * 
	 * <p>
	 * Registers a new activity in the system.
	 * </p>
	 * 
	 * @param request The request body containing the information of the new
	 *                activity.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the creation operation.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Create Activity", description = "Registers a new activity in the system.")
	@ApiResponse(responseCode = "200", description = "Activity created successfully")
	public ResponseEntity<String> createActivity(@RequestBody ActivityDTO request) {
		logger.info("ActivityController :: createActivity");
		ResponseEntity<String> response = null;
		boolean activityExist = activityService.existByDate(request.getDate());
		response = processingResponseI.processStringResponse(activityExist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The activity already exists"), () -> {
					activityService.create(request);
					return ResponseEntity.ok().body("Activity created successfully.");
				});
		return response;
	}

	/**
	 * Retrieves all activities.
	 * 
	 * <p>
	 * Retrieves all activities registered in the system.
	 * </p>
	 * 
	 * @return ResponseEntity<Optional<Set<Activity>>> The response entity
	 *         containing the set of activities, if any.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get All Activities", description = "Retrieves all activities registered in the system.")
	@ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
	public ResponseEntity<List<ActivityDTO>> getActivities() {
		logger.info("ActivityController :: getActivities");
		List<ActivityDTO> activities = activityService.getAll();
		return ResponseEntity.ok().body(activities);
	}

	/**
	 * Retrieves an activity by its ID.
	 * 
	 * <p>
	 * Retrieves an activity with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the activity to be retrieved.
	 * @return ResponseEntity<Optional<Activity>> The response entity containing the
	 *         activity, if found.
	 */
	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get Activity by ID", description = "Retrieves an activity by its ID.")
	@ApiResponse(responseCode = "200", description = "Activity retrieved successfully")
	public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
		logger.info("ActivityController :: getActivityById");
		ResponseEntity<ActivityDTO> response = null;
		ActivityDTO existingActivity = activityService.getOne(id);
		response = processingResponseI.processActivityResponse(existingActivity,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(existingActivity));
		return response;
	}

	/**
	 * Retrieves activities associated with a specific user.
	 * 
	 * <p>
	 * Retrieves a set of activities associated with the specified user.
	 * </p>
	 * 
	 * @param userId The ID of the user whose activities are to be retrieved.
	 * @return ResponseEntity<Optional<Set<Activity>>> The response entity
	 *         containing the set of activities associated with the user, if any.
	 */
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get Activities by User ID", description = "Retrieves activities associated with a specific user.")
	@ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
	public ResponseEntity<List<ActivityDTO>> getActivitiesByUserId(@PathVariable Long userId) {
		logger.info("ActivityController :: getActivities");
		ResponseEntity<List<ActivityDTO>> response = null;
		boolean existingUser = userService.existById(userId);
		response = processingResponseI.processStringResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(activityService.getAllActivities(userId)));
		return response;
	}

	/**
	 * Retrieves the activities created by the user.
	 * 
	 * <p>
	 * Retrieves a set of activities created by the specified user.
	 * </p>
	 * 
	 * @param userId The ID of the user whose created activities are to be
	 *               retrieved.
	 * @return ResponseEntity<Optional<Set<Activity>>> The response entity
	 *         containing the set of created activities, if any.
	 */
	@GetMapping("/created/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Created Activities", description = "Retrieves a set of activities created by the user.")
	@ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
	public ResponseEntity<List<ActivityDTO>> getCreatedActivities(@PathVariable Long userId) {
		logger.info("ActivityController :: getCreatedActivities");
		ResponseEntity<List<ActivityDTO>> response = null;
		boolean existingUser = userService.existById(userId);
		response = processingResponseI.processStringResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(activityService.getCreatedActivities(userId)));
		return response;
	}

	/**
	 * Retrieves the activities in which the user has participated.
	 * 
	 * <p>
	 * Retrieves a set of activities in which the specified user has participated.
	 * </p>
	 * 
	 * @param userId The ID of the user whose participated activities are to be
	 *               retrieved.
	 * @return ResponseEntity<Optional<Set<Activity>>> The response entity
	 *         containing the set of participated activities, if any.
	 */
	@GetMapping("/invited/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Participated Activities", description = "Retrieves a set of activities in which the user has participated.")
	@ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
	public ResponseEntity<List<ActivityDTO>> getInvitedActivities(@PathVariable Long userId) {
		logger.info("ActivityController :: getInvitedActivities");
		ResponseEntity<List<ActivityDTO>> response = null;
		boolean existingUser = userService.existById(userId);

		response = processingResponseI.processStringResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(activityService.getInvitedActivities(userId)));
		return response;
	}

	/**
	 * Retrieves the activities in which the user has participated.
	 * 
	 * <p>
	 * Retrieves a set of activities in which the specified user has participated.
	 * </p>
	 * 
	 * @param userId The ID of the user whose participated activities are to be
	 *               retrieved.
	 * @return ResponseEntity<Optional<Set<Activity>>> The response entity
	 *         containing the set of participated activities, if any.
	 */
	@GetMapping("/creator/{username}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Activities", description = "Retrieves a set of activities in which the user has participated.")
	@ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
	public ResponseEntity<List<ActivityDTO>> getActivitiesByUserName(@PathVariable String username) {
		logger.info("ActivityController :: getInvitedActivities");
		ResponseEntity<List<ActivityDTO>> response = null;
		UserDTO user = userService.getByUserName(username);
		boolean exist = false;
		if (user != null) {
			exist = true;
		}
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(activityService.getAcyivitiesByUsername(username)));
		return response;
	}

	/**
	 * Updates an activity by replacing it with another.
	 * 
	 * <p>
	 * Replaces an existing activity with a new one.
	 * </p>
	 * 
	 * @param id      The ID of the activity to be updated.
	 * @param request The request body containing the new activity information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Update Activity by Replacement", description = "Replaces an existing activity with another.")
	@ApiResponse(responseCode = "200", description = "Activity updated successfully")
	public ResponseEntity<String> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO request) {
		logger.info("ActivityController :: updateActivity");
		ResponseEntity<String> response = null;
		boolean activityExist = activityService.existByDate(request.getDate());
		response = processingResponseI.processStringResponse(activityExist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The activity already exists"), () -> {
					activityService.update(id, request);
					return ResponseEntity.ok().body("Activity updated successfully.");
				});
		return response;
	}

	/**
	 * Partially updates an activity.
	 * 
	 * <p>
	 * Updates some of the attributes of an activity.
	 * </p>
	 * 
	 * @param id      The ID of the activity to be updated.
	 * @param request The request body containing the updated activity information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Partial Update of an Activity", description = "Updates some of the attributes of an activity.")
	@ApiResponse(responseCode = "200", description = "Activity updated successfully")
	public ResponseEntity<String> patchActivity(@PathVariable Long id, @RequestBody ActivityDTO request) {
		logger.info("ActivityController :: patchActivity");
		ResponseEntity<String> response = null;
		boolean activityExist = activityService.existByDate(request.getDate());
		response = processingResponseI.processStringResponse(activityExist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The activity already exists"), () -> {
					activityService.patch(id, request);
					return ResponseEntity.ok().body("Activity updated successfully.");
				});
		return response;
	}

	/**
	 * Deletes an activity.
	 * 
	 * <p>
	 * A user can delete an activity only if they are the creator of it.
	 * </p>
	 * 
	 * @param id The ID of the activity to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	@Operation(summary = "Delete Activity", description = "A user can delete an activity only if they are the creator of it.")
	@ApiResponse(responseCode = "200", description = "Activity deleted successfully")
	public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
		logger.info("ActivityController :: deleteActivity");
		ResponseEntity<String> response = null;
		boolean existingActivity = activityService.existById(id);
		response = processingResponseI.processStringResponse(existingActivity,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND), () -> {
					activityService.deleteById(id);
					return ResponseEntity.ok().body("Activity deleted successfully");
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