package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

public interface ActivityServiceI {

	Optional<Set<Activity>> getAll();

	Optional<Activity> getOne(Long id);

	Boolean existById(Long id);

	Boolean existByDate(Date date);

	ActivityDTO activityToActivityDTO(Activity activity, UserDTO userDTO);

	Activity activityDTOtoActivity(ActivityDTO activityDTO, User user);

	Set<User> participantsDTOtoParticipants(Set<UserDTO> partipantsDTO);

	Set<UserDTO> participantstoParticipantsDTO(Set<User> partipants);

	void create(Activity activity);

	void update(Long id, Activity activity);

	void patch(Long id, Activity activity);

	void deleteById(Long id);

}
