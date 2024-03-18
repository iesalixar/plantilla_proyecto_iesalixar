package com.fitconnet.service.interfaces;

import java.util.List;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.persitence.model.Activity;

public interface ActivityServiceI {

	List<ActivityDTO> getAllActivity();

	ActivityDTO createActivity(Activity activity);

	ActivityDTO updateActivity(Long id, Activity activity);

	ActivityDTO patchActivity(Long id, ActivityDTO activityDTO);

	ActivityDTO deleteById(Long id);

	ActivityDTO getActivity(Long id);

}
