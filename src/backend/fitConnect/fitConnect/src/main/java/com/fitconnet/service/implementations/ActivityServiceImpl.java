package com.fitconnet.service.implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Activity> getAllActivity(Pageable pageable) {

		return activityRepository.findAll(pageable);
	}

	@Override
	public Activity createActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity updateActivity(Long id, Activity activity) {
		Activity findActivity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));

		return activityRepository.save(activity);
	}

	@Override
	public Activity patchActivity(Long id, Activity activity) {
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

		return activity;
	}

	@Override
	public Activity deleteById(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		activityRepository.deleteById(id);

		return activity;
	}

	@Override
	public Activity getActivity(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		return activity;
	}

}