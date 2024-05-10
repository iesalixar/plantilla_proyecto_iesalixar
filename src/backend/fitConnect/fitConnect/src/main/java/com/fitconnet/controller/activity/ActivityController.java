package com.fitconnet.controller.activity;

import java.util.Optional;
import java.util.Set;

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

import com.fitconnet.error.ErrorDetailsResponse;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/activity")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ActivityController {

	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	@Qualifier("userService")
	private final UserServiceI userService;
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<String> createActivity(@RequestBody Activity activity) {
		logger.info("ActivityController :: createActivity");
		ResponseEntity<String> response = null;
		Boolean exist = activityService.existByDate(activity.getDate());

		response = processingResponseI.processResponseForString(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("La actividad ya existe"), () -> {
					Activity newActivity = new Activity();
					activityService.setAttributes(activity, newActivity);
					activityService.create(newActivity);
					return ResponseEntity.ok().body("Actividad creada correctamente.");
				});
		return response;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Set<Activity>>> getActivities() {
		logger.info("ActivityController :: getActivities");
		Optional<Set<Activity>> activities = activityService.getAll();
		return ResponseEntity.ok().body(activities);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Activity>> getActivityById(@PathVariable Long id) {
		logger.info("ActivityController :: getActivityById");
		ResponseEntity<Optional<Activity>> response = null;
		Optional<Activity> existingActivity = activityService.getOne(id);
		response = processingResponseI.processResponseForEntity(existingActivity,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(existingActivity));
		return response;
	}

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Optional<Set<Activity>>> getActivitiesByUserId(@PathVariable Long userId) {
		logger.info("ActivityController :: getActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Boolean existingUser = userService.existById(userId);
		response = processingResponseI.processOptionalResponseForBoolean(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(userService.getAllActivities(userId)));
		return response;
	}

	@GetMapping("/created/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Activity>>> getCreatedActivities(@PathVariable Long userId) {
		logger.info("ActivityController :: getCreatedActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Boolean existingUser = userService.existById(userId);
		response = processingResponseI.processOptionalResponseForBoolean(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(userService.getCreatedActivities(userId)));
		return response;
	}

	@GetMapping("/invited/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Activity>>> getInvitedActivities(@PathVariable Long userId) {
		logger.info("ActivityController :: getInvitedActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Boolean existingUser = userService.existById(userId);
		response = processingResponseI.processOptionalResponseForBoolean(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND),
				() -> ResponseEntity.ok().body(userService.getInvitedActivities(userId)));
		return response;
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<String> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
		logger.info("ActivityController :: updateActivity");
		activityService.update(id, activity);
		return ResponseEntity.ok().body("Se ha actualizado correctamente");
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<String> patchActivity(@PathVariable Long id, @RequestBody Activity activity) {
		logger.info("ActivityController :: patchActivity");
		activityService.patch(id, activity);
		return ResponseEntity.ok().body("Se ha actulizado correctamente");
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
		logger.info("ActivityController :: deleteActivity");
		ResponseEntity<String> response = null;
		boolean existingActivity = activityService.existById(id);
		response = processingResponseI.processResponseForString(existingActivity,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.ACTIVITY_NOT_FOUND), () -> {
					activityService.deleteById(id);
					return ResponseEntity.ok().body("Actividad ha eliminado correctamente");
				});
		return response;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}

}