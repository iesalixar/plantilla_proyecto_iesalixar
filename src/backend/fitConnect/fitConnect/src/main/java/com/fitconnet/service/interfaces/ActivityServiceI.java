package com.fitconnet.service.interfaces;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	Optional<Set<Activity>> getAll();

	Optional<Activity> getOne(Long id);

	Boolean existById(Long id);

	Boolean existByDate(Date date);

	void create(Activity activity);

	void update(Long id, Activity activity);

	void patch(Long id, Activity activity);

	void deleteById(Long id);

	void setAttributes(Activity activity, Activity newActivity);

}
