package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.persitence.model.Comment;

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
	Comment getById(Long id);

	/**
	 * Retrieves all comments.
	 *
	 * @return a list of all comments
	 */
	List<Comment> getAll();

	/**
	 * Checks if a comment exists by its unique identifier.
	 *
	 * @param id the unique identifier of the comment
	 * @return true if the comment exists, false otherwise
	 */
	Boolean existById(Long id);

	/**
	 * Creates a new comment.
	 *
	 * @param comment the comment to create
	 */
	void create(Comment comment);

	/**
	 * Updates an existing comment.
	 *
	 * @param id      the unique identifier of the comment to update
	 * @param comment the updated comment details
	 */
	void update(Long id, Comment comment);

	/**
	 * Deletes a comment by its unique identifier.
	 *
	 * @param id the unique identifier of the comment to delete
	 */
	void delete(Long id);
	/**
	 * Transform a dto into a comment.
	 *
	 * @param dto.
	 */
	Comment commentDtoToComment(CommentDTO dto);
}
