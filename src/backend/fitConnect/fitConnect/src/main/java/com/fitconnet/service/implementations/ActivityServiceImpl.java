package com.fitconnet.service.implementations;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.error.exception.ActivityNotFoundException;
import com.fitconnet.mappers.ActivityMapper;
import com.fitconnet.mappers.UserMapper;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.service.interfaces.ActivityServiceI;

@Service
public class ActivityServiceImpl implements ActivityServiceI {

	private final ActivityRepository activityRepository;
	private final UserMapper userMapper;
	private final ActivityMapper activityMapper;

	public ActivityServiceImpl(ActivityRepository activityRepository, UserMapper userMapper,
			ActivityMapper activityMapper) {
		this.activityRepository = activityRepository;
		this.userMapper = userMapper;
		this.activityMapper = activityMapper;
	}

	@Override
	public List<ActivityDTO> getAllActivity() {
		return activityMapper.toActivityDTOSList(activityRepository.findAll());
	}

	@Override
	public ActivityDTO createActivity(Activity activity) {
		return activityMapper.toActivityDTO(activityRepository.save(activity));
	}

	@Override
	public ActivityDTO updateActivity(Long id, Activity activity) {
		Activity findActivity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));

		Activity saveActivity = activityRepository.save(activity);

		return activityMapper.toActivityDTO(saveActivity);
	}

	@Override
	public ActivityDTO patchActivity(Long id, ActivityDTO activityDTO) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		if (activity.getType() != null) {
			activity.setType(activityDTO.getType());
		}
		if (activity.getDuration() != null) {
			activity.setDuration(activityDTO.getDuration());
		}
		if (activity.getPlace() != null) {
			activity.setPlace(activityDTO.getPlace());
		}
		if (activity.getParticipants() != null) {
			Set<User> participants = userMapper.toUserList(activityDTO.getParticipants());
			activity.setParticipants(participants);
		}

		return activityMapper.toActivityDTO(activity);
	}

	@Override
	public ActivityDTO deleteById(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		activityRepository.deleteById(id);

		return activityMapper.toActivityDTO(activity);
	}

	@Override
	public ActivityDTO getActivity(Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		return activityMapper.toActivityDTO(activity);
	}

}