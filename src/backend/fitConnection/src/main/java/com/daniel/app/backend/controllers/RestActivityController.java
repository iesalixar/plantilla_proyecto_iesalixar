package com.daniel.app.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.app.backend.persistence.model.Activity;
import com.daniel.app.backend.persistence.model.User;
import com.daniel.app.backend.services.interfaces.ActivityServiceI;

@RestController
@RequestMapping("/api/activities")
public class RestActivityController {

	private final ActivityServiceI activityService;

	public RestActivityController(ActivityServiceI activityService) {
		this.activityService = activityService;
	}

	// Operación GET para obtener todas las actividades
	@GetMapping
	public ResponseEntity<List<Activity>> getAllActivities() {
		List<Activity> activities = activityService.getAllActivities();
		if (!activities.isEmpty())
			return new ResponseEntity<>(activities, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Operación GET para obtener actividades por usuario
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Activity>> getActivitiesByUser(@PathVariable Long userId) {
		User user = new User(); // Aquí deberías obtener el usuario por su ID, asumiendo que el ID es el
								// parámetro para identificar al usuario
		List<Activity> activities = activityService.getActivitiesByUser(user);
		if (!activities.isEmpty())
			return new ResponseEntity<>(activities, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Operación GET para obtener actividad por ID
	@GetMapping("/{id}")
	public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
		Activity activity = activityService.getActivityById(id);
		if (activity != null)
			return new ResponseEntity<>(activity, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Operación POST para agregar una nueva actividad
	@PostMapping("/add")
	public ResponseEntity<Void> addActivity(@RequestBody Activity activity) {
		activityService.saveActivity(activity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Operación PUT para actualizar una actividad existente
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
		Activity existingActivity = activityService.getActivityById(id);
		if (existingActivity != null) {
			activity.setIdActivity(id);
			activityService.updateActivity(activity);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Operación DELETE para eliminar una actividad por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
		Activity existingActivity = activityService.getActivityById(id);
		if (existingActivity != null) {
			activityService.deleteActivity(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
