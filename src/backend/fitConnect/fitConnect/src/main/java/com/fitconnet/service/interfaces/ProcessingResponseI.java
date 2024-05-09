package com.fitconnet.service.interfaces;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

public interface ProcessingResponseI {

	<T> ResponseEntity<T> processResponse(Optional<?> entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier);

	<T> ResponseEntity<Optional<T>> processOptionalResponse(Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier);

}
