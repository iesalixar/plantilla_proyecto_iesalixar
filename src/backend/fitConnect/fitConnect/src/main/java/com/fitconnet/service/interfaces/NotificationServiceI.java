package com.fitconnet.service.interfaces;

import java.util.Optional;
import java.util.Set;

import com.fitconnet.persitence.model.Notification;

public interface NotificationServiceI {

	Optional<Set<Notification>> getAll();

	Optional<Set<Notification>> getByRecipient(Long userId);

	void create(Notification notification);

	void delete(Long id);

	void update(Long id, Notification notification);

	void patch(Long id, Notification notification);

}
