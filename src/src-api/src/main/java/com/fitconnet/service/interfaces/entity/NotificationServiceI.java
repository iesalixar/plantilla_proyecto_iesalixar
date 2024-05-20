package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.List;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.persitence.model.Notification;

/**
 * Interface for managing notifications.
 */
public interface NotificationServiceI {

	/**
	 * Retrieves all notifications.
	 *
	 * @return A list of all notifications.
	 */
	List<NotificationDTO> getAll();

	/**
	 * Retrieves the notifications of a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of notifications for the specified user.
	 */
	List<NotificationDTO> getNotifications(Long userId);

	/**
	 * Retrieves a notification by its ID.
	 *
	 * @param id The ID of the notification to retrieve.
	 * @return The notification with the specified ID, if found.
	 */
	NotificationDTO getById(Long id);

	/**
	 * Converts a notification DTO to a notification entity.
	 *
	 * @param dto The notification DTO to convert.
	 * @return The notification entity corresponding to the DTO.
	 */
	Notification notificationDTOtoNotification(NotificationDTO notificationDTO);

	/**
	 * Converts a notification entity to a notification DTO.
	 *
	 * @param dto The notification entity to convert.
	 * @return The notification DTO corresponding to the entity.
	 */
	NotificationDTO notificationToNotificationDTO(Notification notification);

	/**
	 * Checks if a notification exists by its ID.
	 *
	 * @param id The ID of the notification to check.
	 * @return true if a notification exists with the specified ID, false otherwise.
	 */
	boolean existById(Long id);

	/**
	 * Checks if a notification exists for a given date.
	 *
	 * @param date The date to check.
	 * @return true if a notification exists for the given date, false otherwise.
	 */
	boolean existByDate(Date date);

	/**
	 * Creates a new notification.
	 *
	 * @param notification The notification to create.
	 */
	void create(NotificationDTO notification);

	/**
	 * Deletes a notification by its ID.
	 *
	 * @param id The ID of the notification to delete.
	 */
	void delete(Long id);

	/**
	 * Updates an existing notification.
	 *
	 * @param id           The ID of the notification to update.
	 * @param notification The updated notification.
	 */
	void update(Long id, NotificationDTO notification);

	/**
	 * Partially updates an existing notification.
	 *
	 * @param id           The ID of the notification to update.
	 * @param notification The partial update for the notification.
	 */
	void patch(Long id, NotificationDTO notification);

//	/**
//	 * Sets attributes of a notification from another notification.
//	 *
//	 * @param notification    The notification to update.
//	 * @param newNotification The new notification containing updated attributes.
//	 */
//	void setAttributes(NotificationDTO notificationDTO, Notification newNotification);

}
