package com.fitconnet.controller.comment;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.model.User;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.CommentServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class CommentController {
	/**
	 * Dependency injection for the CommentServiceI interface.
	 */
	@Qualifier("commentService")
	private final CommentServiceI commentService;
	/**
	 * Dependency injection for the UserServiceI interface.
	 */
	@Qualifier("userService")
	private final UserServiceI userService;
	/**
	 * Dependency injection for the ActivityServiceI interface.
	 */
	@Qualifier("activityService")
	private final ActivityServiceI activityService;
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
	 * Logger instance for AdminController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(CommentController.class);

	/**
	 * Creates a new activity.
	 * 
	 * <p>
	 * Registers a new activity in the system.
	 * </p>
	 * 
	 * @param request The request body containing the information of the new
	 *                activity.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the creation operation.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Create Comment", description = "Registers a new comment in the activity.")
	@ApiResponse(responseCode = "200", description = "Comment created successfully")
	public ResponseEntity<String> createComment(@RequestBody CommentDTO request) {
		logger.info("CommentController :: createComment");
		ResponseEntity<String> response = null;
		Comment newComment = commentService.commentDtoToComment(request);
		response = processingResponseI.processCommentResponse(newComment,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body("The comment cannot be published"), () -> {
					commentService.create(newComment);
					return ResponseEntity.ok().body("Comment created successfully.");
				});
		return response;
	}

	/**
	 * Retrieves all activities.
	 * 
	 * <p>
	 * Retrieves all comments registered in the system.
	 * </p>
	 * 
	 * @return ResponseEntity<List<Comment>> The response entity containing the list
	 *         of comments, if any.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get All Comments", description = "Retrieves all comments registered in the system.")
	@ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
	public ResponseEntity<List<Comment>> getComments() {
		logger.info("CommentController :: getComments");
		return ResponseEntity.ok().body(commentService.getAll());
	}

	/**
	 * Retrieves an comment by its ID.
	 * 
	 * <p>
	 * Retrieves an comment with the specified ID.
	 * </p>
	 * 
	 * @param id The ID of the comment to be retrieved.
	 * @return ResponseEntity<Optional<Activity>> The response entity containing the
	 *         activity, if found.
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get Comment by ID", description = "Retrieves an comment by its ID.")
	@ApiResponse(responseCode = "200", description = "Comment retrieved successfully")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		logger.info("CommentController :: getCommentById");
		ResponseEntity<Comment> response = null;
		Comment existingComment = commentService.getById(id);
		response = processingResponseI.processCommentResponse(existingComment,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.COMMENT_NOT_FOUND),
				() -> ResponseEntity.ok().body(existingComment));
		return response;
	}

	/**
	 * Retrieves comments associated with a specific user.
	 * 
	 * <p>
	 * Retrieves comments associated with a specific user by user ID.
	 * </p>
	 * 
	 * @param userId The ID of the user whose comments are to be retrieved.
	 * @return ResponseEntity<List<Comment>> The response entity containing the list
	 *         of comments associated with the user, if any.
	 */
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get Comments by User ID", description = "Retrieves comments associated with a specific user.")
	@ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
	@ApiResponse(responseCode = "409", description = "User not found")
	public ResponseEntity<?> getCommentsByUserId(@PathVariable Long userId) {
		logger.info("CommentController :: getCommentsByUserId :: userId={}", userId);
		User user = userService.getById(userId);

		if (user == null) {
			logger.error("User with ID {} not found", userId);
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
		} else {
			List<Comment> comments = userService.getComments(user);
			return ResponseEntity.ok().body(comments);
		}
	}

	/**
	 * Updates an comment by replacing it with another.
	 * 
	 * <p>
	 * Replaces an existing comment with a new one.
	 * </p>
	 * 
	 * @param id      The ID of the comment to be updated.
	 * @param request The request body containing the new comment information.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Update Comment by Replacement", description = "Replaces an existing comment with another.")
	@ApiResponse(responseCode = "200", description = "Comment updated successfully")
	public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody CommentDTO request) {
		logger.info("CommentController :: updateComment");
		ResponseEntity<String> response = null;
		boolean commentExist = commentService.existById(id);
		if (!commentExist) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.COMMENT_NOT_FOUND);
		} else {
			Comment aux = commentService.getById(id);
			request.setUser(aux.getUser());
			request.setActivity(aux.getActivity());
			commentService.commentDtoToComment(request);
			response = ResponseEntity.ok().body("Comment succesfully update");

		}
		return response;
	}

	/**
	 * Deletes an activity.
	 * 
	 * <p>
	 * A user can delete an activity only if they are the creator of it.
	 * </p>
	 * 
	 * @param id The ID of the activity to be deleted.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	@Operation(summary = "Delete Comment", description = "A user can delete a comment only if they are the creator of it.")
	@ApiResponse(responseCode = "200", description = "Comment deleted successfully")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
		logger.info("CommentController :: deleteComment");
		ResponseEntity<String> response = null;
		if (commentService.existById(id)) {
			commentService.delete(id);
			response = ResponseEntity.ok().body("Comment has been succesfully deleted.");
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.COMMENT_NOT_FOUND);
		}

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
