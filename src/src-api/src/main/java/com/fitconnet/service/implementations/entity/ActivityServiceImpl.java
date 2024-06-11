package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.utils.Constants;
import com.fitconnet.utils.Mappers;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityServiceI {
	/**
	 * Repository for activity-related operations.
	 */
	private final ActivityRepository activityRepository;

	/**
	 * Repository for user-related operations.
	 */
	private final UserRepository userRepository;

	/**
	 * Mapper for mapping between activity and User entities and DTOs.
	 */
	private final Mappers mappers;

	private final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Override
	public List<ActivityDTO> getAll() {
		return activityRepository.findAll().stream().map(mappers::activityToActivityDTO).toList();
	}

	@Override
	public ActivityDTO getOne(Long id) {
		Activity response = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND));
		return mappers.activityToActivityDTO(response);
	}

	@Override
	public List<ActivityDTO> getAllActivitiesByUserId(Long id) {
		User creator = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		List<Activity> activityList = new ArrayList<>();
		if (creator != null) {
			activityList = activityRepository.findAllByCreatorId(id);
		}
		return activityList.stream().map(mappers::activityToActivityDTO).toList();
	}

	@Override
	public boolean existById(Long id) {
		return activityRepository.existsById(id);
	}

	@Override
	public void create(ActivityDTO activity) {
		logger.info("ACTIVITY SERVICE:: CREATE: IN");

		Activity newActivity = mappers.activityDTOtoActivity(activity);

		newActivity.setDate(new Date());

		User creator = userRepository.findById(newActivity.getCreator().getId())
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		newActivity.setCreator(creator);

		activityRepository.save(newActivity);

		logger.info("ACTIVITY SERVICE:: CREATE: OUT");
	}

	@Override
	public void update(Long id, ActivityDTO activity) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		activityRepository.save(mappers.activityDTOtoActivity(activity));
	}

	@Override
	public void patch(Long id, ActivityDTO activity) {
	    logger.info("ActivityService :: patchActivity");
	    Activity aux = activityRepository.findById(id)
	            .orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));

	    updateFieldIfDifferent(aux, activity.getTitle(), "title", aux::setTitle);
	    updateFieldIfDifferent(aux, activity.getType(), "type", aux::setType);
	    
	    String newDuration = activity.getDuration() != null ? activity.getDuration() : aux.getDuration();
	    updateFieldIfDifferent(aux, newDuration, "duration", aux::setDuration);
	    
	    updateFieldIfDifferent(aux, activity.getPlace(), "place", aux::setPlace);
	    updateFieldIfDifferent(aux, activity.getImage(), "image", aux::setImage);

	    List<User> participants = Collections.emptyList();
	    if (activity.getParticipants() != null) {
	        participants = activity.getParticipants().stream().map(mappers::userDTOtoUser).toList();
	    }

	    updateFieldIfDifferent(aux, participants, "participants", aux::setParticipants);
	    
	    activityRepository.save(aux);
	}

	@Override
	public void deleteById(Long id) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		activityRepository.deleteById(id);

	}

	private <T> void updateFieldIfDifferent(Activity activity, T newValue, String fieldName, Consumer<T> setter) {
		T existingValue = getFieldValue(activity, fieldName);
		if (newValue != null && !newValue.equals(existingValue) || (newValue == null && existingValue != null)) {
			try {
				setter.accept(newValue);
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("The value for '" + fieldName + "' is not valid.");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getFieldValue(Activity activity, String fieldName) {
		switch (fieldName) {
		case "title":
			return (T) activity.getTitle();
		case "type":
			return (T) activity.getType();
		case "duration":
			return (T) activity.getDuration();
		case "place":
			return (T) activity.getPlace();
		case "participants":
			return (T) activity.getParticipants();
		case "image":
			return (T) activity.getImage();
		default:
			throw new IllegalArgumentException("Unknown field: " + fieldName);
		}
	}
}