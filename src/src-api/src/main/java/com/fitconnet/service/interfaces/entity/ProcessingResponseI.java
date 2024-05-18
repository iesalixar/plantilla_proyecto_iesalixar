package com.fitconnet.service.interfaces.entity;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Comment;
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
	<T> ResponseEntity<T> processEntityResponse(Optional<?> entity, Supplier<ResponseEntity> conflictSupplier,
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
	 * Processes the dual string response.
	 * 
	 * @param condition        the condition for processing
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> procesDualStringResponse(Boolean condition, Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the string response.
	 * 
	 * @param condition        the condition for processing
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<T> processStringResponse(Boolean condition, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the optional response.
	 * 
	 * @param entity           the entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<Optional<T>> processOptionalResponse(Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier);

	/**
	 * Processes the optional boolean response.
	 * 
	 * @param condition        the condition for processing
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<Optional<T>> processOptionalBooleanResponse(Boolean condition,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier);

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
	<T> ResponseEntity<T> processStringDualUserResponse(Boolean condition, User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier);

	/**
	 * Processes the optional user response.
	 * 
	 * @param entity           the user entity to process
	 * @param conflictSupplier supplier for conflict response entity
	 * @param successSupplier  supplier for success response entity
	 * @param <T>              the type of the response entity
	 * @return the ResponseEntity
	 */
	<T> ResponseEntity<Optional<T>> processOptionalUserResponse(User entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<Optional<T>>> successSupplier);
}
