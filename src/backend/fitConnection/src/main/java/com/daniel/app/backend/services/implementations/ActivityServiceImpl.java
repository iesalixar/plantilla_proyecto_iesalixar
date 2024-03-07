package com.daniel.app.backend.services.implementations;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.daniel.app.backend.dtos.ActivityDTO;
import com.daniel.app.backend.persistence.model.Activity;
import com.daniel.app.backend.persistence.model.User;
import com.daniel.app.backend.persistence.repositories.ActivityRepository;
import com.daniel.app.backend.services.interfaces.ActivityServiceI;

/**
 * Implementación del servicio para la gestión de actividades.
 */
@Service
public class ActivityServiceImpl implements ActivityServiceI {

	/** Repositorio para acceder a las actividades en la base de datos. */
	private final ActivityRepository activityRepository;

	/** Utilidad para realizar mapeo de modelos. */
	private final ModelMapper modelMapper;

	/**
	 * Constructor de la clase ActivityServiceImpl.
	 *
	 * @param activityRepository Repositorio de actividades.
	 * @param modelMapper        Utilidad para mapeo de modelos.
	 */
	public ActivityServiceImpl(ActivityRepository activityRepository, ModelMapper modelMapper) {
		this.activityRepository = activityRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Activity> getAllActivities() {
		return activityRepository.findAll().stream().toList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Activity getActivityById(Long id) {
		return activityRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Activity> getActivitiesByUser(User user) {
		return activityRepository.findByUser(user).stream().toList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveActivity(Activity activity) {
		activityRepository.save(activity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateActivity(Activity activity) {
		if (activityRepository.existsById(activity.getIdActivity())) {
			activityRepository.save(activity);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteActivity(Long id) {
		activityRepository.deleteById(id);
	}

	/**
	 * Convierte una entidad {@link Activity} a su correspondiente DTO
	 * {@link ActivityDTO}.
	 *
	 * @param activity La entidad {@link Activity} a convertir.
	 * @return La instancia de {@link ActivityDTO} resultante de la conversión, o
	 *         null si la entidad de entrada es null.
	 */
	private ActivityDTO convertToActivityDTO(Activity activity) {
		return (activity != null) ? modelMapper.map(activity, ActivityDTO.class) : null;
	}

	/**
	 * Convierte un DTO {@link ActivityDTO} a su correspondiente entidad
	 * {@link Activity}.
	 *
	 * @param activityDTO El DTO {@link ActivityDTO} a convertir.
	 * @return La instancia de {@link Activity} resultante de la conversión.
	 */
	private Activity convertToEntity(ActivityDTO activityDTO) {
		return modelMapper.map(activityDTO, Activity.class);
	}
}
