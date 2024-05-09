package com.fitconnet.controller.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.fitconnet.enums.Role;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.Notification;
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
		Optional<User> existingUser = userService.getUserByUserName(user.getUserName());
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe"), () -> {
					User newUser = new User();
					setUserAttributes(user, newUser);
					userService.createUser(newUser);
					return ResponseEntity.ok().body("Usuario: " + user.getUsername() + ", creado correctamente.");
				});
		return response;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
		logger.info("UserController :: getUser");
		ResponseEntity<Optional<User>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(existingUser));
		return response;
	}

	@GetMapping("/friends/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<User>> getFriends(@PathVariable Long id) {
		logger.info("UserController :: getFriends");
		ResponseEntity<List<User>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(existingUser.get().getFriends()));
		return response;
	}

	@GetMapping("/activities/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Activity>>> getActivities(@PathVariable Long id) {
		logger.info("UserController :: getActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processOptionalResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(userService.getAllActivities(id)));
		return response;
	}

	@GetMapping("/activities/created/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Activity>>> getCreatedActivities(@PathVariable Long id) {
		logger.info("UserController :: getCreatedActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processOptionalResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(userService.getCreatedActivities(id)));
		return response;
	}

	@GetMapping("/activities/invited/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Activity>>> getInvitedActivities(@PathVariable Long id) {
		logger.info("UserController :: getInvitedActivities");
		ResponseEntity<Optional<Set<Activity>>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processOptionalResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(userService.getInvitedActivities(id)));
		return response;
	}

	@GetMapping("/notifications/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Set<Notification>>> getNotifications(@PathVariable Long id) {
		logger.info("UserController :: getNotifications");
		ResponseEntity<Optional<Set<Notification>>> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processOptionalResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST),
				() -> ResponseEntity.ok().body(userService.getNotifications(id)));
		return response;
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	public ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody User user) {
		logger.info("UserController :: patchUser");
		ResponseEntity<String> response = null;
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST), () -> {
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
		Optional<User> existingUser = getUserMethod(id);
		response = processingResponseI.processResponse(existingUser,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_EXIST), () -> {
					userService.deleteById(id);
					return ResponseEntity.ok().body("Usuario ha sido eliminado exitosamente");
				});
		return response;
	}

	public Optional<User> getUserMethod(Long id) {
		return userService.getUserById(id);
	}

	private void setUserAttributes(User user, User newUser) {
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUserName(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());// TODO crear password encoder.
		// Asignar el rol de usuario común
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		newUser.setRoles(roles);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponse> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}

}
