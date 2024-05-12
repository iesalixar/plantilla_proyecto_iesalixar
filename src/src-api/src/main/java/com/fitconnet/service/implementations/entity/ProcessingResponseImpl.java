package com.fitconnet.service.implementations.entity;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;

@Service
public class ProcessingResponseImpl implements ProcessingResponseI {

	@Override
	public <T> ResponseEntity<T> processEntityResponse(Optional<?> entity, Supplier<ResponseEntity> conflictSupplier,
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
	public <T> ResponseEntity<T> processStringResponse(Boolean condition, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (condition.booleanValue()) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<Optional<T>> processOptionalBooleanResponse(Boolean condition,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier) {
		ResponseEntity<Optional<T>> outcome;
		if (!condition) {
			outcome = successSupplier.get();
		} else {
			outcome = conflictSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> procesDualStringResponse(Boolean condition, Optional<?> entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (!condition) {
			outcome = successSupplier.get();
		} else {
			outcome = conflictSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processStringDualUserResponse(Boolean condition, User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (!condition) {
			outcome = successSupplier.get();
		} else {
			outcome = conflictSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<Optional<T>> processOptionalUserResponse(User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<Optional<T>>> successSupplier) {

		ResponseEntity<Optional<T>> outcome;

		if (entity == null) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}
}
