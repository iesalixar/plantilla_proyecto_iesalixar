package com.fitconnet.service.implementations;

import java.security.InvalidParameterException;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import com.fitconnet.error.exception.notifications.NotificationCreationException;
import com.fitconnet.error.exception.notifications.NotificationNotFoundException;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.repository.NotificationRepository;
import com.fitconnet.service.interfaces.NotificationServiceI;

import jakarta.validation.ConstraintViolationException;

public class NotificationServiceImpl implements NotificationServiceI {
	private final NotificationRepository notificationRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Override
	public Set<Notification> getAll() {
		return (Set<Notification>) notificationRepository.findAll();
	}

	@Override
	public Notification getByRecipient(Long userId) {
		Notification notification = notificationRepository.findById(userId)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not fund", HttpStatus.NOT_FOUND));
		return notification;
	}

	@Override
	public Notification create(Notification notification) {
		Notification aux = new Notification();
		try {
			aux = notificationRepository.save(notification);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NotificationCreationException("Error creating notification", e, HttpStatus.BAD_REQUEST);
		}
		return aux;
	}

	@Override
	public Notification delete(Long id) {

		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found", HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);
		return notification;
	}

	@Override
	public Notification update(Long id, Notification notification) {
		Notification aux = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found", HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);

		return notificationRepository.save(aux);
	}

	@Override
	public Notification patch(Long id, Notification notification) {
		Notification aux = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found", HttpStatus.NOT_FOUND));
		try {
			if (notification.getMessage() != aux.getMessage()) {
				aux.setMessage(notification.getMessage());
			}

		} catch (ConstraintViolationException e) {
			throw new InvalidParameterException("Debe ser un memsaje válido.");
		}
		try {
			if (notification.getDate() != aux.getDate()) {
				aux.setDate(notification.getDate());
			}

		} catch (ConstraintViolationException e) {
			throw new InvalidParameterException("Debe ser una fecha válida.");
		}
		return aux;
	}

}
