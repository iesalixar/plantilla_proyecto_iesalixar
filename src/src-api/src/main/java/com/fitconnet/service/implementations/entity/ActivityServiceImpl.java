package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityServiceI {

	private final ActivityRepository activityRepository;
	private final UserServiceI userService;

	@Override
	public List<Activity> getAll() {

		return activityRepository.findAll();
	}

	@Override
	public Activity getOne(Long id) {
		return activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public void create(Activity activity) {
		activityRepository.save(activity);
	}

	@Override
	public void update(Long id, Activity activity) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		activityRepository.save(activity);
	}

	@Override
	public void patch(Long id, Activity activity) {
		Activity aux = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		updateFieldIfDifferent(aux, activity.getType(), "type", aux::setType);
		updateFieldIfDifferent(aux, activity.getDuration(), "duration", aux::setDuration);
		updateFieldIfDifferent(aux, activity.getPlace(), "place", aux::setPlace);
		updateFieldIfDifferent(aux, activity.getParticipants(), "participants", aux::setParticipants);
		updateFieldIfDifferent(aux, activity.getDate(), "date", aux::setDate);
	}

	@Override
	public void deleteById(Long id) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		activityRepository.deleteById(id);

	}

	@Override
	public boolean existById(Long id) {
		return activityRepository.existsById(id);
	}

	@Override
	public boolean existByDate(Date date) {
		return activityRepository.existByDate(date);
	}

	@Override
	public Activity activityDTOtoActivity(ActivityDTO activityDTO, User user) {
		Activity Activity = new Activity();
		Activity.setCreator(user);
		Activity.setDate(activityDTO.getDate());
		Activity.setDuration(activityDTO.getDuration());
		Activity.setParticipants(participantsDTOtoParticipants(activityDTO.getParticipants()));
		Activity.setPlace(activityDTO.getPlace());
		Activity.setType(activityDTO.getType());

		return Activity;

	}

	@Override
	public ActivityDTO activityToActivityDTO(Activity activity, UserDTO userDTO) {
		ActivityDTO dto = new ActivityDTO();
		dto.setCreator(userDTO);
		dto.setDate(activity.getDate());
		dto.setDuration(activity.getDuration());
		dto.setParticipants(participantstoParticipantsDTO(activity.getParticipants()));
		dto.setPlace(activity.getPlace());
		dto.setType(activity.getType());

		return dto;
	}

	@Override
	public List<User> participantsDTOtoParticipants(List<UserDTO> partipantsDTO) {
		User user = new User();
		List<User> participats = new ArrayList<>();

		for (UserDTO dto : partipantsDTO) {
			Set<Role> roles = dto.getRoles();
			Role rol = Role.ROLE_USER;

			if (!roles.contains(Role.ROLE_USER)) {
				rol = Role.ROLE_ADMIN;
			}
			userService.userDTOtoUSer(dto, user, rol);
		}

		return participats;
	}

	@Override
	public List<UserDTO> participantstoParticipantsDTO(List<User> partipants) {

		List<UserDTO> partipantsDTO = new ArrayList<>();
		UserDTO dto = new UserDTO();

		for (User user : partipants) {
			Set<Role> roles = user.getRoles();
			Role rol = Role.ROLE_USER;

			if (!roles.contains(Role.ROLE_USER)) {
				rol = Role.ROLE_ADMIN;
			}
			userService.usertoUserDTO(dto, user, rol);
		}

		return partipantsDTO;
	}

	private <T> void updateFieldIfDifferent(Activity activity, T newValue, String fieldName, Consumer<T> setter) {
		T existingValue = getFieldValue(activity, fieldName);
		if (newValue != null && !newValue.equals(existingValue)) {
			try {
				setter.accept(newValue);
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("El valor para '" + fieldName + "' no es válido.");
			}
		}
	}

	private <T> T getFieldValue(Activity activity, String fieldName) {
		switch (fieldName) {
		case "type":
			return (T) activity.getType();
		case "duration":
			return (T) activity.getDuration();
		case "place":
			return (T) activity.getPlace();
		case "participants":
			return (T) activity.getParticipants();
		case "date":
			return (T) activity.getDate();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}