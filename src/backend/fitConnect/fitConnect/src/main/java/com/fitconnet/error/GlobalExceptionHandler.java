package com.fitconnet.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.error.exception.ActivityNotFoundException;
import com.fitconnet.error.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetailsResponse> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleLibroNotFoundException(UserNotFoundException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ActivityNotFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleLibroNotFoundException(ActivityNotFoundException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Ruta no encontrada",
				ex.getRequestURL());

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception ex, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Error interno del servidor",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetailsResponse> handleAccessDeniedException(AccessDeniedException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Acceso denegado",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

}