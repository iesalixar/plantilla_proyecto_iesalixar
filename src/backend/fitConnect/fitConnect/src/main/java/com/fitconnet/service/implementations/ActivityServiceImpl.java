package com.fitconnet.service.implementations;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.service.interfaces.ActivityServiceI;

@Service
public class ActivityServiceImpl implements ActivityServiceI {

	private final ActivityRepository activityRepository;

	public ActivityServiceImpl(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;

	}

	@Override
	public Optional<Set<Activity>> getAllActivity() {
		List<Activity> activitiesList = activityRepository.findAll();
		Set<Activity> sortedActivities = new LinkedHashSet();
		sortedActivities = activitiesList.stream().sorted(Comparator.comparing(Activity::getDate))
				.collect(Collectors.toSet());
		return Optional.of(sortedActivities);
	}

	@Override
	public Optional<Activity> getActivity(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		return Optional.of(activity);
	}

	@Override
	public void createActivity(Activity activity) {
		activityRepository.save(activity);
	}

	@Override
	public void updateActivity(Long id, Activity activity) {
		Activity findActivity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));

		activityRepository.save(activity);
	}

	@Override
	public void patchActivity(Long id, Activity activity) {
		Activity aux = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		if (aux.getType() != null) {
			aux.setType(activity.getType());
		}
		if (aux.getDuration() != null) {
			aux.setDuration(activity.getDuration());
		}
		if (aux.getPlace() != null) {
			aux.setPlace(activity.getPlace());
		}

	}

	@Override
	public void deleteById(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		activityRepository.deleteById(id);

	}

}