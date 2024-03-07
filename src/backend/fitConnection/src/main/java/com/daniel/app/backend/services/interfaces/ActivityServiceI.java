package com.daniel.app.backend.services.interfaces;

import java.util.List;

import com.daniel.app.backend.dtos.ActivityDTO;
import com.daniel.app.backend.persistence.model.Activity;
import com.daniel.app.backend.persistence.model.User;

/**
 * Interfaz que define los servicios relacionados con las actividades.
 */
public interface ActivityServiceI {

	/**
	 * Recupera todas las actividades.
	 *
	 * @return Lista de {@link ActivityDTO} que representa todas las actividades.
	 */
	List<Activity> getAllActivities();

	/**
	 * Recupera una actividad por su identificador único.
	 *
	 * @param id Identificador único de la actividad a recuperar.
	 * @return {@link ActivityDTO} que representa la actividad con el identificador
	 *         proporcionado.
	 */
	Activity getActivityById(Long id);

	/**
	 * Recupera todas las actividades asociadas a un usuario específico.
	 *
	 * @param user El usuario del cual se quieren recuperar las actividades.
	 * @return Lista de {@link ActivityDTO} que representa las actividades asociadas
	 *         al usuario proporcionado.
	 */
	List<Activity> getActivitiesByUser(User user);

	/**
	 * Guarda una nueva actividad.
	 *
	 * @param activityDTO La actividad a guardar.
	 */
	void saveActivity(Activity activity);

	/**
	 * Actualiza una actividad existente.
	 *
	 * @param activityDTO La actividad a actualizar.
	 */
	void updateActivity(Activity activity);

	/**
	 * Elimina una actividad por su identificador único.
	 *
	 * @param id Identificador único de la actividad a eliminar.
	 */
	void deleteActivity(Long id);
}
