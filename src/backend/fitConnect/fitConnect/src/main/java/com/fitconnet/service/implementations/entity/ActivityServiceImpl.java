package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityServiceI {

	private final ActivityRepository activityRepository;

	@Override
	public Optional<Set<Activity>> getAll() {
		List<Activity> activitiesList = activityRepository.findAll();
		Set<Activity> sortedActivities;
		sortedActivities = activitiesList.stream().sorted(Comparator.comparing(Activity::getDate))
				.collect(Collectors.toSet());
		return Optional.of(sortedActivities);
	}

	@Override
	public Optional<Activity> getOne(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(activity);
	}

	@Override
	public void create(Activity activity) {
		activityRepository.save(activity);
	}

	@Override
	public void update(Long id, Activity activity) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		activityRepository.save(activity);
	}

	@Override
	public void patch(Long id, Activity activity) {
		Activity aux = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		updateFieldIfDifferent(aux, activity.getType(), "type", aux::setType);
		updateFieldIfDifferent(aux, activity.getDuration(), "duration", aux::setDuration);
		updateFieldIfDifferent(aux, activity.getPlace(), "place", aux::setPlace);
		updateFieldIfDifferent(aux, activity.getParticipants(), "participants", aux::setParticipants);
		updateFieldIfDifferent(aux, activity.getDate(), "date", aux::setDate);
	}

	@Override
	public void deleteById(Long id) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		activityRepository.deleteById(id);

	}

	@Override
	public Boolean existById(Long id) {
		return activityRepository.existsById(id);
	}

	@Override
	public Boolean existByDate(Date date) {
		return activityRepository.existByDate(date);
	}

	@Override
	public void setAttributes(Activity activity, Activity newActivity) {
		newActivity.setCreator(activity.getCreator());
		newActivity.setDate(activity.getDate());
		newActivity.setDuration(activity.getDuration());
		newActivity.setParticipants(activity.getParticipants());
		newActivity.setPlace(activity.getPlace());
		newActivity.setType(activity.getType());

	}

	private <T> void updateFieldIfDifferent(Activity activity, T newValue, String fieldName, Consumer<T> setter) {
		T existingValue = getFieldValue(activity, fieldName);
		if (newValue != null && !newValue.equals(existingValue)) {
			try {
				setter.accept(newValue);
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("El valor para '" + fieldName + "' no es válido.");
			}
		}
	}

	private <T> T getFieldValue(Activity activity, String fieldName) {
		switch (fieldName) {
		case "type":
			return (T) activity.getType();
		case "duration":
			return (T) activity.getDuration();
		case "place":
			return (T) activity.getPlace();
		case "participants":
			return (T) activity.getParticipants();
		case "date":
			return (T) activity.getDate();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}