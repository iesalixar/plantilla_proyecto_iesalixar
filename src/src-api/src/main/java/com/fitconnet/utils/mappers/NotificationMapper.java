package com.fitconnet.utils.mappers;

import org.springframework.stereotype.Component;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.persitence.model.Notification;

import lombok.AllArgsConstructor;

/**
 * Mapper class for mapping between Notification entities and DTOs.
 */
@Component
@AllArgsConstructor
public class NotificationMapper {

	/**
	 * Mapper for mapping between user entities and DTOs.
	 */
	private final UserMapper userMapper;

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
			newNotification.setReceiver(userMapper.userDTOtoUser(notificationDTO.getReceiver()));
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
			notificationDTO.setReceiver(userMapper.userToUserDTO(notification.getReceiver()));
			return notificationDTO;
		}
	}

}
