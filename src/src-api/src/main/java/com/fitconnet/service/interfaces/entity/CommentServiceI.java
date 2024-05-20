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
	CommentDTO getById(Long id);

	/**
	 * Retrieves all comments.
	 *
	 * @return a list of all comments
	 */
	List<CommentDTO> getAll();

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
	void create(CommentDTO comment);

	/**
	 * Updates an existing comment.
	 *
	 * @param id      the unique identifier of the comment to update
	 * @param comment the updated comment details
	 */
	void update(Long id, CommentDTO comment);

	/**
	 * Deletes a comment by its unique identifier.
	 *
	 * @param id the unique identifier of the comment to delete
	 */
	void delete(Long id);

	/**
	 * Transform a dto into a comment.
	 *
	 * @param dto the DTO to transform
	 * @return the transformed Comment object
	 */
	Comment commentDtoToComment(CommentDTO dto);

	/**
	 * Transform a comment into a dto.
	 *
	 * @param comment the Comment object to transform
	 * @return the transformed CommentDTO object
	 */
	CommentDTO commentToCommentDTO(Comment comment);
}
