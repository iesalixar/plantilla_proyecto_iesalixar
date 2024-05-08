package com.fitconnet.error;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ IllegalArgumentException.class, UserNotFoundException.class, ActivityNotFoundException.class })
	public ResponseEntity<ErrorDetailsResponse> handleCommonExceptions(Exception ex, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST
				: ex instanceof UserNotFoundException || ex instanceof ActivityNotFoundException ? HttpStatus.NOT_FOUND
						: HttpStatus.INTERNAL_SERVER_ERROR;
		logException(ex);
		return new ResponseEntity<>(errorDetails, status);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Ruta no encontrada",
				ex.getRequestURL());
		logException(ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetailsResponse> handleAccessDeniedException(AccessDeniedException ex,
			WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Acceso denegado",
				request.getDescription(false));
		logException(ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception ex, WebRequest request) {
		ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Error interno del servidor",
				request.getDescription(false));
		logException(ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void logException(Exception ex) {
		logger.error("Se ha producido una excepción:", ex);
	}
}
