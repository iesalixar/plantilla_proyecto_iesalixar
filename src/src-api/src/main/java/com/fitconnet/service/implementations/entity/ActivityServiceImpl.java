package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.fitconnet.persitence.repository.UserRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.utils.Constants;
import com.fitconnet.utils.mappers.ActivityMapper;
import com.fitconnet.utils.mappers.CommentMapper;
import com.fitconnet.utils.mappers.UserMapper;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityServiceI {
	/**
	 * Repository for activity-related operations.
	 */
	private final ActivityRepository activityRepository;

	/**
	 * Repository for user-related operations.
	 */
	private final UserRepository userRepository;

	/**
	 * Mapper for mapping between activity entities and DTOs.
	 */
	private final ActivityMapper activityMapper;

	/**
	 * Mapper for mapping between user entities and DTOs.
	 */
	private final UserMapper userMapper;

	/**
	 * Mapper for mapping between comment entities and DTOs.
	 */
	private final CommentMapper commentMapper;

	private final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Override
	public List<ActivityDTO> getAll() {
		return activityRepository.findAll().stream().map(activityMapper::activityToActivityDTO).toList();
	}

	@Override
	public List<ActivityDTO> getAcyivitiesByUsername(String username) {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getCreatedActivities().stream().map(activity -> activityMapper.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public List<ActivityDTO> getAcyivitiesByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getCreatedActivities().stream().map(activity -> activityMapper.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public ActivityDTO getOne(Long id) {
		Activity response = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND));
		return activityMapper.activityToActivityDTO(response);
	}

	@Override
	public List<ActivityDTO> getCreatedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getCreatedActivities().stream().map(activity -> activityMapper.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public List<ActivityDTO> getInvitedActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		return user.getInvitedActivities().stream().map(activity -> activityMapper.activityToActivityDTO(activity))
				.toList();
	}

	@Override
	public List<ActivityDTO> getAllActivities(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

		List<Activity> allActivities = new ArrayList<>();
		allActivities.addAll(user.getCreatedActivities());
		allActivities.addAll(user.getInvitedActivities());

		return allActivities.stream().map(activityMapper::activityToActivityDTO).toList();
	}

	@Override
	public List<String> getAllImages() {
		return activityRepository.findAll().stream().map(Activity::getImage).toList();
	}

	@Override
	public boolean existById(Long id) {
		return activityRepository.existsById(id);
	}

//	@Override
//	public boolean existByDate(Date date) {
//		return activityRepository.existByDate(date);
//	}

	@Override
	public void create(ActivityDTO activity) {
		logger.info("ACTIVITY SERVICE:: CREATE: IN");
		Activity newActivity = activityMapper.activityDTOtoActivity(activity);
		LocalDateTime now = LocalDateTime.now();
		Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		newActivity.setDate(date);
		activityRepository.save(newActivity);
	}

	@Override
	public void update(Long id, ActivityDTO activity) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

		activityRepository.save(activityMapper.activityDTOtoActivity(activity));
	}

	@Override
	public void patch(Long id, ActivityDTO activity) {
		Activity aux = activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFoundException("Activity not found", HttpStatus.NOT_FOUND));
		updateFieldIfDifferent(aux, activity.getType(), "type", aux::setType);
		// updateFieldIfDifferent(aux, activity.getDuration(), "duration",
		// aux::setDuration);
		updateFieldIfDifferent(aux, activity.getPlace(), "place", aux::setPlace);
		updateFieldIfDifferent(aux, activity.getLikes(), "like", aux::setLikes);
		updateFieldIfDifferent(aux, activity.getImage(), "image", aux::setImage);
		updateFieldIfDifferent(aux, activity.getParticipants().stream().map(userMapper::userDTOtoUser).toList(),
				"participants", aux::setParticipants);
		updateFieldIfDifferent(aux, activity.getComments().stream().map(commentMapper::commentDtoToComment).toList(),
				"comment", aux::setComments);

	}

	@Override
	public void deleteById(Long id) {
		if (!activityRepository.findById(id).isPresent()) {
			throw new ActivityNotFoundException(Constants.ACTIVITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		activityRepository.deleteById(id);

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
//		case "duration":
//			return (T) activity.getDuration();
		case "place":
			return (T) activity.getPlace();
		case "participants":
			return (T) activity.getParticipants();
		case "image":
			return (T) activity.getImage();
		case "like":
			return (T) activity.getLikes();
		case "comment":
			return (T) activity.getComments();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}