package com.fitconnet.service.interfaces;

import java.util.Optional;
import java.util.Set;

import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	Optional<Set<Activity>> getAllActivity();

	Optional<Activity> getActivity(Long id);

	void createActivity(Activity activity);

	void updateActivity(Long id, Activity activity);

	void patchActivity(Long id, Activity activity);

	void deleteById(Long id);

}
