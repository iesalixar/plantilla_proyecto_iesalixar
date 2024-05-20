package com.fitconnet.service.interfaces.entity;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

/**
 * Interface for processing response entities.
 */
public interface ProcessingResponseI {

	/**
	 * Processes the entity response.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processUserResponse(User entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the entity response.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processNotificationResponse(Notification entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the entity response.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processActivityResponse(Activity entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the entity response.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processCommentResponse(Comment entity, Supplier<ResponseEntity> conflictSupplier,
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

	/**
	 * Processes the string dual user response.
	 * 
	 * @param condition        the condition for processing
	 * @param entity           the user entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processStringDualUserResponse(boolean condition, User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier);

}
