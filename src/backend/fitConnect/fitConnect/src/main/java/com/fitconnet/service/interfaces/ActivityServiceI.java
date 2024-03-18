package com.fitconnet.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	Page<Activity> getAllActivity(Pageable pageable);

	ActivityDTO createActivity(Activity activity);

	ActivityDTO updateActivity(Long id, Activity activity);

	ActivityDTO patchActivity(Long id, ActivityDTO activityDTO);

	ActivityDTO deleteById(Long id);

	ActivityDTO getActivity(Long id);

}
