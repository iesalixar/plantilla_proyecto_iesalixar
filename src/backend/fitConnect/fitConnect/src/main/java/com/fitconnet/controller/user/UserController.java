package com.fitconnet.controller.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.error.ErrorDetailsResponse;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.ProcessingResponseI;
import com.fitconnet.service.interfaces.UserServiceI;
import com.fitconnet.utils.Constants;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Qualifier("userService")
	private final UserServiceI userService;
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserServiceI userService, GlobalExceptionHandler globalExceptionHandler,
			ProcessingResponseI processingResponseI) {

		this.userService = userService;
		this.globalExceptionHandler = globalExceptionHandler;
		this.processingResponseI = processingResponseI;
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) {
		logger.info("UserController :: createUser");
		ResponseEntity<String> response = null;
		Boolean existingUser = userService.existByEmail(user.getEmail());
		response = processingResponseI.processResponseForString(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe"), () -> {
					User newUser = new User();
					userService.setUserAttributes(user, newUser);
					userService.createUser(newUser);
					return ResponseEntity.ok().body("Usuario: " + user.getUsername() + ", creado correctamente.");
				});
		return response;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> showUsers() {
		logger.info("## AdminController :: showUsers");
		List<User> userList = userService.userSetToSortedList();
		return ResponseEntity.ok().body(userList);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
		logger.info("UserController :: getUser");
		ResponseEntity<Optional<User>> response = null;
		Optional<User> existingUser = userService.getUserById(id);
		response = processingResponseI.processResponseForUser(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND),
				() -> ResponseEntity.ok().body(existingUser));
		return response;
	}

	@GetMapping("/friends/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<User>> getFriends(@PathVariable Long id) {
		logger.info("UserController :: getFriends");
		ResponseEntity<List<User>> response = null;
		Optional<User> existingUser = userService.getUserById(id);
		response = processingResponseI.processResponseForUser(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND),
				() -> ResponseEntity.ok().body(existingUser.get().getFriends()));
		return response;
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody User user) {
		logger.info("UserController :: patchUser");
		ResponseEntity<String> response = null;
		Boolean existingUser = userService.existById(id);
		response = processingResponseI.processResponseForString(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.patchUser(id, user);
					return ResponseEntity.ok().body("Usuario actualizado");
				});
		return response;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.info("UserController :: deleteUser");
		ResponseEntity<String> response = null;
		Boolean existingUser = userService.existById(id);
		response = processingResponseI.processResponseForString(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.deleteById(id);
					return ResponseEntity.ok().body("Usuario ha sido eliminado exitosamente");
				});
		return response;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}

}
