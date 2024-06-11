package com.fitconnet.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

import lombok.AllArgsConstructor;

/**
 * Mapper class for mapping between User and Activity entities and DTOs.
 */
@Component
@AllArgsConstructor
public class Mappers {

	// USER MAPPER LOGIC

	/**
	 * Converts a UserDTO object to a User object.
	 *
	 * @param userDTO The UserDTO object to be converted.
	 * @return The corresponding User object.
	 */
	public User userDTOtoUser(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		} else {
			User user = new User();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setAge(userDTO.getAge());
			user.setEmail(userDTO.getEmail());
			user.setPassword(userDTO.getPassword());
			user.setImage(userDTO.getImage());
			user.setRoles(userDTO.getRoles());
			user.setFriends(userDTOsToUsers(userDTO.getFriends()));
			user.setNotifications(notificationsDToToNotifications(userDTO.getNotifications()));
			return user;
		}
	}

	/**
	 * Converts a User object to a UserDTO object.
	 *
	 * @param user The User object to be converted.
	 * @return The corresponding UserDTO object.
	 */
	public UserDTO userToUserDTO(User user) {
		if (user == null) {
			return null;
		} else {
			UserDTO response = new UserDTO();
			response.setId(user.getId());
			response.setName(user.getName());
			response.setAge(user.getAge());
			response.setEmail(user.getEmail());
			response.setImage(user.getImage());
			response.setPassword(user.getPassword());
			response.setRoles(user.getRoles());
			response.setFriends(usersToUserDTOs(user.getFriends()));
			response.setNotifications(notificationsToNotificationsDTO(user.getNotifications()));
			return response;
		}
	}

	/**
	 * Converts a list of UserDTO objects to a list of User objects.
	 *
	 * @param dtos The list of UserDTO objects to be converted.
	 * @return The corresponding list of User objects.
	 */
	public List<User> userDTOsToUsers(List<UserDTO> dtos) {
		if (dtos == null) {
			return Collections.emptyList();
		}
		return dtos.stream().map(this::userDTOtoUser).toList();
	}

	/**
	 * Converts a list of User objects to a list of UserDTO objects.
	 *
	 * @param users The list of User objects to be converted.
	 * @return The corresponding list of UserDTO objects.
	 */
	public List<UserDTO> usersToUserDTOs(List<User> users) {
		if (users == null) {
			return Collections.emptyList();
		}
		return users.stream().map(this::userToUserDTO).toList();
	}

	// ACTIVITY MAPPER LOGIC

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
			activity.setId(activityDTO.getId());
			activity.setTitle(activityDTO.getTitle());
			activity.setType(activityDTO.getType());
			activity.setDuration(activityDTO.getDuration());
			activity.setPlace(activityDTO.getPlace());
			activity.setCreator(userDTOtoUser(activityDTO.getCreator()));
			activity.setParticipants(userDTOsToUsers(activityDTO.getParticipants()));
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
			dto.setTitle(activity.getTitle());
			dto.setCreator(userToUserDTO(activity.getCreator()));
			dto.setDate(activity.getDate());
			dto.setDuration(activity.getDuration());
			dto.setParticipants(usersToUserDTOs(activity.getParticipants()));
			dto.setPlace(activity.getPlace());
			dto.setType(activity.getType());
			dto.setImage(activity.getImage());
			return dto;
		}
	}

	/**
	 * Converts a list of {@link Activity} entities to a list of {@link ActivityDTO}
	 * objects.
	 *
	 * @param activityList the list of {@link Activity} entities to convert. If the
	 *                     list is null, an empty list will be returned.
	 * @return a list of {@link ActivityDTO} objects corresponding to the given
	 *         {@link Activity} entities.
	 */
	public List<ActivityDTO> activitiesToActivititesDTO(List<Activity> activityList) {
		if (activityList == null) {
			return Collections.emptyList();
		}
		return activityList.stream().map(this::activityToActivityDTO).toList();
	}

	/**
	 * Converts a list of {@link ActivityDTO} objects to a list of {@link Activity}
	 * entities.
	 *
	 * @param activityDTOList the list of {@link ActivityDTO} objects to convert. If
	 *                        the list is null, an empty list will be returned.
	 * @return a list of {@link Activity} entities corresponding to the given
	 *         {@link ActivityDTO} objects.
	 */
	public List<Activity> activitiesDTotToActivitites(List<ActivityDTO> activityDTOList) {
		if (activityDTOList == null) {
			return Collections.emptyList();
		}
		return activityDTOList.stream().map(this::activityDTOtoActivity).toList();
	}

	// COMMENT MAPPER LOGIC

	/**
	 * Converts a NotificationDTO object to a Notification object.
	 *
	 * @param notificationDTO The NotificationDTO object to be converted.
	 * @return The corresponding Notification object.
	 */
	public Notification notificationDTOtoNotification(NotificationDTO notificationDTO) {
		if (notificationDTO == null) {
			return null;
		} else {
			Notification newNotification = new Notification();
			newNotification.setDate(notificationDTO.getDate());
			newNotification.setMessage(notificationDTO.getMessage());
			newNotification.setReceiver(userDTOtoUser(notificationDTO.getReceiver()));
			return newNotification;
		}
	}

	/**
	 * Converts a Notification object to a NotificationDTO object.
	 *
	 * @param notification The Notification object to be converted.
	 * @return The corresponding NotificationDTO object.
	 */
	public NotificationDTO notificationToNotificationDTO(Notification notification) {
		if (notification == null) {
			return null;
		} else {
			NotificationDTO notificationDTO = new NotificationDTO();
			notificationDTO.setDate(notification.getDate());
			notificationDTO.setMessage(notification.getMessage());
			notificationDTO.setReceiver(userToUserDTO(notification.getReceiver()));
			return notificationDTO;
		}
	}

	/**
	 * Converts a list of {@link Notification} entities to a list of
	 * {@link NotificationDTO} objects.
	 *
	 * @param notificationList the list of {@link Notification} entities to convert.
	 *                         If the list is null, an empty list will be returned.
	 * @return a list of {@link NotificationDTO} objects corresponding to the given
	 *         {@link Notification} entities.
	 */
	public List<NotificationDTO> notificationsToNotificationsDTO(List<Notification> notificationList) {
		if (notificationList == null) {
			return Collections.emptyList();
		}
		return notificationList.stream().map(this::notificationToNotificationDTO).toList();
	}

	/**
	 * Converts a list of {@link NotificationDTO} objects to a list of
	 * {@link Notification} entities.
	 *
	 * @param notificationDTOList the list of {@link NotificationDTO} objects to
	 *                            convert. If the list is null, an empty list will
	 *                            be returned.
	 * @return a list of {@link Notification} entities corresponding to the given
	 *         {@link NotificationDTO} objects.
	 */
	public List<Notification> notificationsDToToNotifications(List<NotificationDTO> notificationDTOList) {
		if (notificationDTOList == null) {
			return Collections.emptyList();
		}
		return notificationDTOList.stream().map(this::notificationDTOtoNotification).toList();
	}
}
