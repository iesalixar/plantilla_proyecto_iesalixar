package com.fitconnet.controller.activity;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.service.interfaces.ActivityServiceI;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	private final Logger LOG = LoggerFactory.getLogger(ActivityController.class);

	public ActivityController(ActivityServiceI activityService, GlobalExceptionHandler globalExceptionHandler) {
		this.activityService = activityService;
		this.globalExceptionHandler = globalExceptionHandler;
	}

	@GetMapping
	public ResponseEntity<Optional<Set<Activity>>> getAllActivities() {
		LOG.info("ActivityController :: getAllActivities");
		Optional<Set<Activity>> activities = activityService.getAllActivity();
		return ResponseEntity.ok(activities);
	}

	@PostMapping
	public ResponseEntity<String> createActivity(@RequestBody Activity activity) {
		LOG.info("ActivityController :: createActivity");
		activityService.createActivity(activity);
		return ResponseEntity.ok("El usuario ha sido creado correctamente.");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Activity>> getActivityById(@PathVariable Long id) {
		LOG.info("ActivityController :: getActivityById");
		Optional<Activity> activity = activityService.getActivity(id);
		return ResponseEntity.ok(activity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
		LOG.info("ActivityController :: updateActivity");
		activityService.updateActivity(id, activity);
		return ResponseEntity.ok("Se ha actualizado correctamente");
	}

	@PatchMapping("/{id}")
	public ResponseEntity<String> patchActivity(@PathVariable Long id, @RequestBody Activity activity) {
		LOG.info("ActivityController :: patchActivity");
		activityService.patchActivity(id, activity);
		return ResponseEntity.ok("Se ha actulizado correctamente");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
		LOG.info("ActivityController :: deleteActivity");
		activityService.deleteById(id);
		return ResponseEntity.ok("Se ha eliminado correcatamente");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}

}