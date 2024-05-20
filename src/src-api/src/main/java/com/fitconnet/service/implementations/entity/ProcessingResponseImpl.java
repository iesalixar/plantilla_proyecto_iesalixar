package com.fitconnet.service.implementations.entity;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;

@Service
public class ProcessingResponseImpl implements ProcessingResponseI {

	@Override
	public <T> ResponseEntity<T> processUserResponse(User entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (entity == null) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processNotificationResponse(Notification entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (entity == null) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processStringResponse(boolean condition, Supplier<ResponseEntity> conflictSupplier,
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
	public <T> ResponseEntity<T> processCommentResponse(Comment entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (entity == null) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processActivityResponse(Activity entity, Supplier<ResponseEntity> conflictSupplier,
			Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (entity == null) {
			outcome = conflictSupplier.get();
		} else {
			outcome = successSupplier.get();
		}
		return outcome;
	}

	@Override
	public <T> ResponseEntity<T> processStringDualUserResponse(boolean condition, User entity,
			Supplier<ResponseEntity> conflictSupplier, Supplier<ResponseEntity<T>> successSupplier) {
		ResponseEntity<T> outcome;
		if (!condition) {
			outcome = successSupplier.get();
		} else {
			outcome = conflictSupplier.get();
		}
		return outcome;
	}
}
