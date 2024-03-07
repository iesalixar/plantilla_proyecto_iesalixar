package com.daniel.app.backend.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daniel.app.backend.persistence.model.Activity;
import com.daniel.app.backend.persistence.model.User;
import com.daniel.app.backend.services.interfaces.ActivityServiceI;
import com.daniel.app.backend.services.interfaces.UserServiceI;
import com.daniel.app.backend.utilities.Constants;

@Controller
public class ActivityController {

	private final ActivityServiceI activityService;
	private final UserServiceI userService;

	public ActivityController(ActivityServiceI activityService, UserServiceI userService) {
		this.activityService = activityService;
		this.userService = userService;
	}

	@PostMapping("/activity")
	public String activityHandler(@RequestParam String userName, @RequestParam String activityType,
			@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam Double distance,
			@RequestParam String place, Model model) {

		User existingUser = userService.getUserByUserName(userName);

		// Crear una nueva actividad
		Activity newActivity = new Activity();
		newActivity.setActivityType(activityType);
		newActivity.setStartTime(startTime);
		newActivity.setEndTime(endTime);
		newActivity.setDistance(distance);
		newActivity.setPlace(place);
		newActivity.setUser(existingUser); // Asociar la actividad con el usuario

		// Guardar la actividad después de asociarla al usuario
		activityService.saveActivity(newActivity);

		// Actualizar la lista de actividades del usuario
		List<Activity> activities = existingUser.getActivities();
		activities.add(newActivity);
		existingUser.setActivities(activities);

		// Actualizar el usuario en la base de datos
		userService.updateUser(existingUser);

		model.addAttribute("idUser", existingUser.getIdUser());

		return Constants.USER_PROFILE;
	}

	@PostMapping("/updateActivity")
	public String showUpdateForm(@RequestParam Long idActivity, Model model) {
		// Recuperar la actividad por su ID
		Activity activity = activityService.getActivityById(idActivity);
		if (activity != null) {
			model.addAttribute("activity", activity);
			return "activities/update-form"; // Ruta al formulario de actualización
		} else {
			// Manejar el caso en que no se encuentre la actividad
			return Constants.USER_PROFILE;
		}
	}

	@PostMapping("/activity/update/{idActivity}")
	public String updateActivity(@PathVariable Long idActivity, @RequestParam String activityType,
			@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam Double distance,
			@RequestParam String place, Model model) {
		// Recuperar la actividad por su ID
		Activity activity = activityService.getActivityById(idActivity);
		if (activity != null) {
			// Obtener el usuario asociado a la actividad
			User user = activity.getUser();

			// Actualizar los datos de la actividad
			activity.setActivityType(activityType);
			activity.setStartTime(startTime);
			activity.setEndTime(endTime);
			activity.setDistance(distance);
			activity.setPlace(place);

			// Guardar la actividad actualizada
			activityService.saveActivity(activity);

			// Actualizar la lista de actividades del usuario
			List<Activity> activities = user.getActivities();
			activities.add(activity);

			// Agregar información del usuario al modelo
			model.addAttribute("idUser", user.getIdUser());

			// Redirigir al perfil del usuario después de la actualización
		}
		return Constants.USER_PROFILE;

	}

	@PostMapping("/deleteActivity")
	public String deleteActivity(@RequestParam Long idActivity, Model model) {
		// Obtener la actividad antes de eliminarla para obtener al usuario asociado
		Activity activity = activityService.getActivityById(idActivity);

		if (activity != null) {
			// Obtener el usuario asociado a la actividad
			User user = activity.getUser();

			// Eliminar la actividad por su ID
			activityService.deleteActivity(idActivity);

			// Agregar información del usuario al modelo
			model.addAttribute("idUser", user.getIdUser());

		}

		return Constants.USER_PROFILE;

	}
}
