package com.fitconnet.dto.entities;

import lombok.Data;

@Data
public class CommentDTO {
	/**
	 * The content of the comment.
	 */
	private String content;
	/**
	 * The user who made the comment.
	 */
	private UserDTO user;
	/**
	 * The activity associated with the comment.
	 */
	private ActivityDTO activity;

}
