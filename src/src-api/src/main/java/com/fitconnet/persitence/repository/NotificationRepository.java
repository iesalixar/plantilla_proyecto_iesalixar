package com.fitconnet.persitence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

/**
 * Repository interface for managing notifications.
 */
@Repository("notificationRepository")
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	/**
	 * Finds notifications by recipient.
	 *
	 * @param user An optional containing the user who is the recipient of the
	 *             notifications.
	 * @return An optional containing a set of notifications received by the
	 *         specified user.
	 */
	@Query("SELECT n FROM Notification n WHERE n.receiver = ?1")
	List<Notification> findByRecipient(User user);

	/**
	 * Checks if a notification exists for a given date.
	 *
	 * @param date The date to check.
	 * @return true if a notification exists for the given date, false otherwise.
	 */
	@Query("SELECT COUNT(n) > 0 FROM Notification n WHERE n.date = ?1")
	Boolean existByDate(Date date);
}