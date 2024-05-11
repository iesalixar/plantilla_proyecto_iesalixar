package com.fitconnet.service.interfaces.entity;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.fitconnet.persitence.model.User;

public interface ProcessingResponseI {

	<T> ResponseEntity<T> processEntityResponse(Optional<?> entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	<T> ResponseEntity<T> procesDualStringResponse(Boolean condition, Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier);

	<T> ResponseEntity<T> processStringResponse(Boolean condition, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	<T> ResponseEntity<Optional<T>> processOptionalResponse(Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier);

	<T> ResponseEntity<Optional<T>> processOptionalBooleanResponse(Boolean condition,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier);

	<T> ResponseEntity<T> processStringDualUserResponse(Boolean condition, User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier);

	<T> ResponseEntity<Optional<T>> processOptionalUserResponse(User entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<Optional<T>>> successSupplier);

}
