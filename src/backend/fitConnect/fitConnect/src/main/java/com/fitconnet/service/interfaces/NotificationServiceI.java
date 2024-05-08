package com.fitconnet.service.interfaces;

import java.util.Set;

import com.fitconnet.persitence.model.Notification;

public interface NotificationServiceI {

	Set<Notification> getAll();

	Notification getByRecipient(Long userId);

	Notification create(Notification notification);

	Notification delete(Long id);

	Notification update(Long id, Notification notification);

	Notification patch(Long id, Notification notification);

}
