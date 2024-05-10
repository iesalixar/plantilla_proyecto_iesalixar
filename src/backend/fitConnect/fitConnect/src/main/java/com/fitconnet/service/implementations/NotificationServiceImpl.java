package com.fitconnet.service.implementations;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.error.exception.notifications.NotificationCreationException;
import com.fitconnet.error.exception.notifications.NotificationNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.NotificationRepository;
import com.fitconnet.service.interfaces.NotificationServiceI;
import com.fitconnet.utils.Constants;

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
		Set<Notification> sortedNotifications;
		sortedNotifications = notificationList.stream().sorted(Comparator.comparing(Notification::getDate))
				.collect(Collectors.toSet());
		return Optional.of(sortedNotifications);
	}

	@Override
	public Optional<Notification> getById(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		return Optional.of(notification);
	}

	@Override
	public Optional<Set<Notification>> getByRecipient(Optional<User> user) {
		Optional<Set<Notification>> notifications = notificationRepository.findByRecipient(user);
		if (notifications.isEmpty()) {
			throw new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return notifications;
	}

	@Override
	public void create(Notification notification) {

		try {
			notificationRepository.save(notification);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NotificationCreationException("Error creating notification", e, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void delete(Long id) {

		if (!notificationRepository.findById(id).isPresent()) {
			throw new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		notificationRepository.deleteById(id);
	}

	@Override
	public void update(Long id, Notification notification) {
		Notification aux = notificationRepository.findById(id).orElseThrow(
				() -> new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);
		notificationRepository.save(aux);

	}

	@Override
	public void patch(Long id, Notification notification) {
		Notification aux = notificationRepository.findById(id).orElseThrow(
				() -> new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		updateFieldIfDifferent(aux, notification.getMessage(), "message", aux::setMessage);
		updateFieldIfDifferent(aux, notification.getDate(), "date", aux::setDate);
		updateFieldIfDifferent(aux, notification.getReceiver(), "receiver", aux::setReceiver);

	}

	@Override
	public boolean existById(Long id) {
		return notificationRepository.existsById(id);
	}

	@Override
	public void setAttributes(Notification notification, Notification newNotification) {
		newNotification.setDate(notification.getDate());
		newNotification.setMessage(notification.getMessage());
		newNotification.setReceiver(notification.getReceiver());

	}

	@Override
	public boolean existByDate(Date date) {
		return notificationRepository.existByDate(date);
	}

	private <T> void updateFieldIfDifferent(Notification notification, T newValue, String fieldName,
			Consumer<T> setter) {
		T existingValue = getFieldValue(notification, fieldName);
		if (newValue != null && !newValue.equals(existingValue)) {
			try {
				setter.accept(newValue);
			} catch (ConstraintViolationException e) {
				throw new InvalidParameterException("El valor para '" + fieldName + "' no es válido.");
			}
		}
	}

	private <T> T getFieldValue(Notification notification, String fieldName) {
		switch (fieldName) {
		case "message":
			return (T) notification.getMessage();
		case "date":
			return (T) notification.getDate();
		case "receiverId":
			return (T) notification.getReceiver();
		default:
			throw new IllegalArgumentException("Campo desconocido: " + fieldName);
		}
	}

}
