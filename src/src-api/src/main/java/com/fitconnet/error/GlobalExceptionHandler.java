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

import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.exception.activity.ActivityNotFoundException;
import com.fitconnet.error.exception.user.UserNotFoundException;

/**
 * Global exception handler class for handling various types of exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handles common exceptions such as IllegalArgumentException,
	 * UserNotFoundException, and ActivityNotFoundException.
	 *
	 * @param ex      The exception that occurred.
	 * @param request The web request associated with the exception.
	 * @return ResponseEntity containing details of the error.
	 */
	@ExceptionHandler({ IllegalArgumentException.class, UserNotFoundException.class, ActivityNotFoundException.class })
	public ResponseEntity<ErrorDetailsDTO> handleCommonExceptions(Exception ex, WebRequest request) {
		// Construct error details
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(new Date(), ex.getMessage(), request.getDescription(false));

		// Determine appropriate HTTP status code based on the type of exception
		HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST
				: ex instanceof UserNotFoundException || ex instanceof ActivityNotFoundException ? HttpStatus.NOT_FOUND
						: HttpStatus.INTERNAL_SERVER_ERROR;

		// Log the exception
		logException(ex);

		// Return ResponseEntity with error details and status code
		return new ResponseEntity<>(errorDetails, status);
	}

	/**
	 * Handles NoHandlerFoundException, which occurs when no handler is found for a
	 * given request.
	 *
	 * @param ex      The NoHandlerFoundException that occurred.
	 * @param request The web request associated with the exception.
	 * @return ResponseEntity containing details of the error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		// Construct error details
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(new Date(), "Ruta no encontrada", ex.getRequestURL());

		// Log the exception
		logException(ex);

		// Return ResponseEntity with error details and NOT_FOUND status code
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles AccessDeniedException, which occurs when access to a resource is
	 * denied.
	 *
	 * @param ex      The AccessDeniedException that occurred.
	 * @param request The web request associated with the exception.
	 * @return ResponseEntity containing details of the error.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetailsDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		// Construct error details
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(new Date(), "Acceso denegado",
				request.getDescription(false));

		// Log the exception
		logException(ex);

		// Return ResponseEntity with error details and FORBIDDEN status code
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	/**
	 * Handles general exceptions that are not specifically caught by other
	 * handlers.
	 *
	 * @param ex      The exception that occurred.
	 * @param request The web request associated with the exception.
	 * @return ResponseEntity containing details of the error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsDTO> handleGlobalException(Exception ex, WebRequest request) {
		// Construct error details
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(new Date(), "Error interno del servidor",
				request.getDescription(false));

		// Log the exception
		logException(ex);

		// Return ResponseEntity with error details and INTERNAL_SERVER_ERROR status
		// code
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Logs the exception.
	 *
	 * @param ex The exception to log.
	 */
	private void logException(Exception ex) {
		logger.error("An Exception has occurred:", ex);
	}
}
