package com.fitconnet.service.interfaces;

import java.util.List;

import com.fitconnet.persitence.model.Notification;

public interface NotificationServiceI {

	List<Notification> getAll();

	Notification getByRecipient(Long userId);

	Notification create(Notification notification);

	Notification delete(Long id);

	Notification update(Long id, Notification notification);

	Notification patch(Long id, Notification notification);

}
