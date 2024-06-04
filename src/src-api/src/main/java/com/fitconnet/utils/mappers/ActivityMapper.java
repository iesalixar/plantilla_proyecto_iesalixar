package com.fitconnet.utils.mappers;

import org.springframework.stereotype.Component;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.persitence.model.Activity;

import lombok.AllArgsConstructor;

/**
 * Mapper class for mapping between Activity entities and DTOs.
 */
@Component
@AllArgsConstructor
public class ActivityMapper {
	/**
	 * Mapper for mapping between user entities and DTOs.
	 */
	private final UserMapper userMapper;

	/**
	 * Converts an ActivityDTO object to an Activity object.
	 *
	 * @param activityDTO The ActivityDTO object to be converted.
	 * @return The corresponding Activity object.
	 */
	public Activity activityDTOtoActivity(ActivityDTO activityDTO) {
		if (activityDTO == null) {
			return null;
		} else {
			Activity activity = new Activity();
			activity.setTitle(activityDTO.getTitle());
			activity.setType(activityDTO.getType());
			// activity.setDuration(activityDTO.getDuration());
			activity.setPlace(activityDTO.getPlace());
			activity.setLikes(activityDTO.getLikes());
			activity.setCreator(userMapper.userDTOtoUser(activityDTO.getCreator()));
			activity.setParticipants(userMapper.userDTOsToUsers(activityDTO.getParticipants()));
			activity.setImage(activityDTO.getImage());
			return activity;
		}

	}

	/**
	 * Converts an Activity object to an ActivityDTO object.
	 *
	 * @param activity The Activity object to be converted.
	 * @return The corresponding ActivityDTO object.
	 */
	public ActivityDTO activityToActivityDTO(Activity activity) {
		if (activity == null) {
			return null;
		} else {
			ActivityDTO dto = new ActivityDTO();
			dto.setId(activity.getId());
			dto.setCreator(userMapper.userToUserDTO(activity.getCreator()));
			dto.setDate(activity.getDate());
//			dto.setDuration(activity.getDuration());
			dto.setParticipants(userMapper.usersToUserDTOs(activity.getParticipants()));
			dto.setPlace(activity.getPlace());
			dto.setType(activity.getType());
			dto.setLikes(activity.getLikes());
			dto.setImage(activity.getImage());
			return dto;
		}
	}
}
