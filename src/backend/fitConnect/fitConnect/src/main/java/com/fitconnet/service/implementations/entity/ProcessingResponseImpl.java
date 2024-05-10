package com.fitconnet.service.implementations.entity;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fitconnet.service.interfaces.entity.ProcessingResponseI;

@Service
public class ProcessingResponseImpl implements ProcessingResponseI {

	@Override
	public <T> ResponseEntity<T> processResponseForEntity(Optional<?> entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (!entity.isPresent()) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<Optional<T>> processOptionalResponse(Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier) {
		ResponseEntity<Optional<T>> outcome;
		if (!entity.isPresent()) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processResponseForString(Boolean condition, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (!condition) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<Optional<T>> processOptionalResponseForBoolean(Boolean condition,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier) {
		ResponseEntity<Optional<T>> outcome;
		if (!condition) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

}
