package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import com.fitconnet.utils.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.error.exception.notifications.NotificationCreationException;
import com.fitconnet.error.exception.notifications.NotificationNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.repository.NotificationRepository;
import com.fitconnet.service.interfaces.entity.NotificationServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationServiceI {

	/**
	 * Repository for notification-related operations.
	 */
	private final NotificationRepository notificationRepository;

	/**
	 * Mapper for mapping between notification entities and DTOs.
	 */
	private final Mappers mapper;

	@Override
	public List<NotificationDTO> getAll() {
		return notificationRepository.findAll().stream().map(mapper::notificationToNotificationDTO)
				.toList();
	}

	@Override
	public NotificationDTO getById(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		return mapper.notificationToNotificationDTO(notification);
	}

	@Override
	public void create(NotificationDTO notificationDTO) {

		try {
			notificationRepository.save(mapper.notificationDTOtoNotification(notificationDTO));
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
	public void update(Long id, NotificationDTO notificationDTO) {
		notificationRepository.findById(id).orElseThrow(
				() -> new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		notificationRepository.deleteById(id);
		notificationRepository.save(mapper.notificationDTOtoNotification(notificationDTO));

	}

	@Override
	public void patch(Long id, NotificationDTO notificationDTO) {
		Notification aux = notificationRepository.findById(id).orElseThrow(
				() -> new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		Notification notification = mapper.notificationDTOtoNotification(notificationDTO);
		updateFieldIfDifferent(aux, notification.getMessage(), "message", aux::setMessage);
		updateFieldIfDifferent(aux, notification.getDate(), "date", aux::setDate);
		updateFieldIfDifferent(aux, notification.getReceiver(), "receiver", aux::setReceiver);

	}

	@Override
	public boolean existById(Long id) {
		return notificationRepository.existsById(id);
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
