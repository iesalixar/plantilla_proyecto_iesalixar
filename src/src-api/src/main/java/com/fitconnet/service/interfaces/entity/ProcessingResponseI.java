package com.fitconnet.service.interfaces.entity;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;

/**
 * Interface for processing response entities.
 */
public interface ProcessingResponseI {

	/**
	 * Processes the entity response for UserDTO.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processUserResponse(UserDTO entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the entity response for NotificationDTO.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processNotificationResponse(NotificationDTO entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the entity response for ActivityDTO.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processActivityResponse(ActivityDTO entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the string response.
	 * 
	 * @param condition        the condition for processing
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processStringResponse(boolean condition, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

}
