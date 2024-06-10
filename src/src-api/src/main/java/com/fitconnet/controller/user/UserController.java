package com.fitconnet.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.enums.Role;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT, RequestMethod.DELETE })
@AllArgsConstructor
public class UserController {
	/**
	 * Dependency injection for the UserServiceI interface.
	 */
	@Qualifier("userService")
	private final UserServiceI userService;
	/**
	 * Dependency injection for the ProcessingResponseI interface.
	 */
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;
	/**
	 * Dependency injection for the GlobalExceptionHandler.
	 */
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	/**
	 * Logger instance for UserController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Creates a new user.
	 * 
	 * <p>
	 * Registers a new user in the system.
	 * </p>
	 * 
	 * @param user The request body containing the information of the new user.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the creation operation.
	 */
	@PostMapping
	@Operation(summary = "Create User", description = "Registers a new user in the system.")
	@ApiResponse(responseCode = "200", description = "User created successfully")
	public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
		logger.info("UserController :: createUser");
		boolean exist = userService.existByEmail(user.getEmail());
		return processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The user already exists"), () -> {
					Role role = Role.ROLE_USER;
					userService.create(user);
					return ResponseEntity.ok().body("User: " + user.getName() + ", created successfully.");
				});
	}

	/**
	 * Retrieves all users.
	 * 
	 * <p>
	 * Retrieves all users registered in the system.
	 * </p>
	 * 
	 * @return ResponseEntity<List<User>> The response entity containing the list of
	 *         users.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Show Users", description = "Retrieves all users registered in the system.")
	@ApiResponse(responseCode = "200", description = "Users retrieved successfully")
	public ResponseEntity<List<UserDTO>> showUsers() {
		logger.info("## UserController :: showUsers");
		List<UserDTO> userList = userService.getAll();
		return ResponseEntity.ok().body(userList);
	}

	/**
	 * Retrieves a user by ID.
	 * 
	 * <p>
	 * Retrieves the user with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the user to be retrieved.
	 * @return ResponseEntity<Optional<User>> The response entity containing the
	 *         user, if found.
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Get User by ID", description = "Retrieves a user by ID.")
	@ApiResponse(responseCode = "200", description = "User retrieved successfully")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		logger.info("UserController :: getUser");
		ResponseEntity<UserDTO> response = null;
		UserDTO user = userService.getById(id);
		response = processingResponseI.processUserResponse(user,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND),
				() -> ResponseEntity.ok().body(user));
		return response;
	}

	/**
	 * Retrieves friends of a user by ID.
	 * 
	 * <p>
	 * Retrieves the friends of the user with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the user whose friends are to be retrieved.
	 * @return ResponseEntity<List<User>> The response entity containing the list of
	 *         friends of the user, if any.
	 */
	@GetMapping("/friends/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Friends by User ID", description = "Retrieves friends of a user by ID.")
	@ApiResponse(responseCode = "200", description = "Friends retrieved successfully")
	public ResponseEntity<List<UserDTO>> getFriends(@PathVariable Long id) {
		logger.info("UserController :: getFriends");
		ResponseEntity<List<UserDTO>> response = null;
		UserDTO user = userService.getById(id);
		response = processingResponseI.processUserResponse(user,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND),
				() -> ResponseEntity.ok().body(user.getFriends()));
		return response;
	}

	/**
	 * Updates a user.
	 * 
	 * <p>
	 * Updates an existing user with new information.
	 * </p>
	 * 
	 * @param id   The ID of the user to be updated.
	 * @param user The request body containing the updated user information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	@Operation(summary = "Update User", description = "Updates an existing user with new information.")
	@ApiResponse(responseCode = "200", description = "User updated successfully")
	public ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody UserDTO user) {
		logger.info("UserController :: patchUser");
		ResponseEntity<String> response = null;
		Boolean exist = userService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.patch(id, user);
					return ResponseEntity.ok().body("User updated successfully.");
				});
		return response;
	}

	/**
	 * Deletes a user.
	 * 
	 * <p>
	 * Deletes the user with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the user to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	@Operation(summary = "Delete User", description = "Deletes a user.")
	@ApiResponse(responseCode = "200", description = "User deleted successfully")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.info("UserController :: deleteUser");
		ResponseEntity<String> response = null;
		Boolean exist = userService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.USER_NOT_FOUND), () -> {
					userService.deleteById(id);
					return ResponseEntity.ok().body("User has been deleted successfully.");
				});
		return response;
	}

	/**
	 * Handles NoHandlerFoundException.
	 * 
	 * <p>
	 * Handles the case when no handler is found for a request.
	 * </p>
	 * 
	 * @param ex      The NoHandlerFoundException instance.
	 * @param request The web request.
	 * @return ResponseEntity<ErrorDetailsResponse> The response entity containing
	 *         details of the error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}

}
