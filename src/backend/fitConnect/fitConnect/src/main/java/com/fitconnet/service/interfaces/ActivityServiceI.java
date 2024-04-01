package com.fitconnet.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	Page<Activity> getAllActivity(Pageable pageable);

	Activity createActivity(Activity activity);

	Activity updateActivity(Long id, Activity activity);

	Activity patchActivity(Long id, Activity activity);

	Activity deleteById(Long id);

	Activity getActivity(Long id);

}
