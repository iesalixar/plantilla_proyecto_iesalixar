package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

/**
 * Interface for managing notifications.
 */
public interface NotificationServiceI {

	/**
	 * Retrieves all notifications.
	 *
	 * @return An optional containing a set of all notifications, if any.
	 */
	Optional<Set<Notification>> getAll();

	/**
	 * Retrieves notifications by recipient.
	 *
	 * @param user An optional containing the user who is the recipient of the
	 *             notifications.
	 * @return An optional containing a set of notifications received by the
	 *         specified user.
	 */
	Optional<Set<Notification>> getByRecipient(Optional<User> user);

	/**
	 * Retrieves a notification by its ID.
	 *
	 * @param id The ID of the notification to retrieve.
	 * @return An optional containing the notification with the specified ID, if
	 *         found.
	 */
	Optional<Notification> getById(Long id);

	Notification DtoToNotification(NotificationDTO dto);

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
	void create(Notification notification);

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
	void update(Long id, Notification notification);

	/**
	 * Partially updates an existing notification.
	 *
	 * @param id           The ID of the notification to update.
	 * @param notification The partial update for the notification.
	 */
	void patch(Long id, Notification notification);

	/**
	 * Sets attributes of a notification from another notification.
	 *
	 * @param notification    The notification to update.
	 * @param newNotification The new notification containing updated attributes.
	 */
	void setAttributes(Notification notification, Notification newNotification);

}