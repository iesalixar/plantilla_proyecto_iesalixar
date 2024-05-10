package com.fitconnet.service.interfaces;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.fitconnet.persitence.model.Notification;

public interface NotificationServiceI {

	Optional<Set<Notification>> getAll();

	Optional<Set<Notification>> getByRecipient(Long userId);

	Optional<Notification> getById(Long id);

	boolean existById(Long id);

	boolean existByDate(Date date);

	void create(Notification notification);

	void delete(Long id);

	void update(Long id, Notification notification);

	void patch(Long id, Notification notification);

	void setAttributes(Notification notification, Notification newNotification);

}
