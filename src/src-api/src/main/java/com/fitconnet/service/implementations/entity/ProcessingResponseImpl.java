package com.fitconnet.service.implementations.entity;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.NotificationDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;

@Service
public class ProcessingResponseImpl implements ProcessingResponseI {

	@Override
	public <T> ResponseEntity<T> processUserResponse(UserDTO entity, Supplier<ResponseEntity> conflictSupplier,
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
	public <T> ResponseEntity<T> processNotificationResponse(NotificationDTO entity,
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
	public <T> ResponseEntity<T> processActivityResponse(ActivityDTO entity, Supplier<ResponseEntity> conflictSupplier,
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

}
