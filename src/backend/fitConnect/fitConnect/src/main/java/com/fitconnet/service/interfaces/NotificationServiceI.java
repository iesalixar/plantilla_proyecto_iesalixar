package com.fitconnet.service.interfaces;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fitconnet.persitence.model.Notification;

public interface NotificationServiceI {

	Set<Notification> getAll();

	Page<Notification> getByRecipient(Long userId, Pageable pageable);

	Notification create(Notification notification);

	Notification delete(Long id);

	Notification update(Long id, Notification notification);

	Notification patch(Long id, Notification notification);

}
