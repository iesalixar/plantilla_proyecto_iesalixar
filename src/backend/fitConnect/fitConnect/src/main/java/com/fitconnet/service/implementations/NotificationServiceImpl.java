package com.fitconnet.service.implementations;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.notifications.NotificationCreationException;
import com.fitconnet.error.exception.notifications.NotificationNotFoundException;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.repository.NotificationRepository;
import com.fitconnet.service.interfaces.NotificationServiceI;

import jakarta.validation.ConstraintViolationException;

@Service
public class NotificationServiceImpl implements NotificationServiceI {
	private final NotificationRepository notificationRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Override
	public Optional<Set<Notification>> getAll() {
		List<Notification> notificationList = notificationRepository.findAll();
		Set<Notification> sortedNotifications = new LinkedHashSet<>();
		sortedNotifications = notificationList.stream().sorted(Comparator.comparing(Notification::getDate))
				.collect(Collectors.toSet());
		return Optional.of(sortedNotifications);
	}

	@Override
	public Optional<Set<Notification>> getByRecipient(Long userId) {
		Optional<Set<Notification>> notifications = notificationRepository.findByRecipientId(userId);
		if (notifications.isEmpty()) {
			throw new NotificationNotFoundException("Notifications not found for user with ID: " + userId,
					HttpStatus.NOT_FOUND);
		}
		return notifications;
	}

	@Override
	public void create(Notification notification) {
		Notification aux = new Notification();
		try {
			aux = notificationRepository.save(notification);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NotificationCreationException("Error creating notification", e, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void delete(Long id) {

		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found", HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);
	}

	@Override
	public void update(Long id, Notification notification) {
		Notification aux = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found", HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);
		notificationRepository.save(aux);

	}

	@Override
	public void patch(Long id, Notification notification) {
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
	}

}
