package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.dto.entities.UserDTO;

/**
 * Interface for the Comment Service.
 */
public interface CommentServiceI {

	/**
	 * Retrieves a comment by its unique identifier.
	 *
	 * @param id the unique identifier of the comment
	 * @return the comment with the specified id
	 */
	CommentDTO getById(Long id);

	/**
	 * Retrieves all comments.
	 *
	 * @return a list of all comments
	 */
	List<CommentDTO> getAll();

	/**
	 * Retrieves the comments of a user.
	 * 
	 * @param user The user.
	 * @return A list of comments by the specified user.
	 */
	List<CommentDTO> getComments(UserDTO user);

	/**
	 * Checks if a comment exists by its unique identifier.
	 *
	 * @param id the unique identifier of the comment
	 * @return true if the comment exists, false otherwise
	 */
	boolean existById(Long id);

	/**
	 * Creates a new comment.
	 *
	 * @param comment the comment to create
	 */
	void create(CommentDTO commentDTO);

	/**
	 * Updates an existing comment.
	 *
	 * @param id      the unique identifier of the comment to update
	 * @param comment the updated comment details
	 */
	void update(Long id, CommentDTO commentDTO);

	/**
	 * Deletes a comment by its unique identifier.
	 *
	 * @param id the unique identifier of the comment to delete
	 */
	void delete(Long id);

}
