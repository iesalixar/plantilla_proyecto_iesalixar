package com.fitconnet.service.interfaces;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	Optional<Set<Activity>> getAllActivity();

	Optional<Activity> getActivity(Long id);

	Boolean existActivity(Long id);

	Boolean existByDate(Date date);

	void createActivity(Activity activity);

	void updateActivity(Long id, Activity activity);

	void patchActivity(Long id, Activity activity);

	void deleteById(Long id);

	void setActivityAttributes(Activity activity, Activity newActivity);

}
