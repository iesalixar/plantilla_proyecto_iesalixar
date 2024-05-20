package com.fitconnet.service.implementations.entity;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.error.exception.notifications.NotificationCreationException;
import com.fitconnet.error.exception.notifications.NotificationNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.repository.NotificationRepository;
import com.fitconnet.service.interfaces.entity.NotificationServiceI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationServiceI {
	private final NotificationRepository notificationRepository;
	private final UserServiceI userService;

	@Override
	public List<NotificationDTO> getAll() {
		return notificationRepository.findAll().stream().map(this::notificationToNotificationDTO).toList();
	}

	@Override
	public NotificationDTO getById(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		return notificationToNotificationDTO(notification);
	}

	@Override
	public List<NotificationDTO> getByRecipient(UserDTO user) {
		List<NotificationDTO> notifications = notificationRepository.findByRecipient(userService.userDTOtoUser(user))
				.stream().map(this::notificationToNotificationDTO).toList();
		if (notifications.isEmpty()) {
			throw new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return notifications;
	}

	@Override
	public void create(NotificationDTO notificationDTO) {

		try {
			notificationRepository.save(notificationDTOtoNotification(notificationDTO));
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
		notificationRepository.save(notificationDTOtoNotification(notificationDTO));

	}

	@Override
	public void patch(Long id, NotificationDTO notificationDTO) {
		Notification aux = notificationRepository.findById(id).orElseThrow(
				() -> new NotificationNotFoundException(Constants.NOTIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
		Notification notification = notificationDTOtoNotification(notificationDTO);
		updateFieldIfDifferent(aux, notification.getMessage(), "message", aux::setMessage);
		updateFieldIfDifferent(aux, notification.getDate(), "date", aux::setDate);
		updateFieldIfDifferent(aux, notification.getReceiver(), "receiver", aux::setReceiver);

	}

	@Override
	public boolean existById(Long id) {
		return notificationRepository.existsById(id);
	}

//	@Override
//	public void setAttributes(NotificationDTO notificationDTO, Notification newNotification) {
//		newNotification.setDate(notification.getDate());
//		newNotification.setMessage(notification.getMessage());
//		newNotification.setReceiver(notification.getReceiver());
//
//	}

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

	@Override
	public Notification notificationDTOtoNotification(NotificationDTO dto) {
		Notification newNotification = new Notification();
		newNotification.setDate(dto.getDate());
		newNotification.setMessage(dto.getMessage());
		newNotification.setReceiver(userService.userDTOtoUser(dto.getReceiver()));
		return newNotification;
	}

	@Override
	public NotificationDTO notificationToNotificationDTO(Notification notification) {
		NotificationDTO notificationDTO = new NotificationDTO();
		notificationDTO.setDate(notification.getDate());
		notificationDTO.setMessage(notification.getMessage());
		notificationDTO.setReceiver(userService.userToUserDTO(notification.getReceiver()));
		return notificationDTO;
	}

}
