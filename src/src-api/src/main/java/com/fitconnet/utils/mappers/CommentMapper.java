package com.fitconnet.utils.mappers;

import org.springframework.stereotype.Component;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.persitence.model.Comment;

import lombok.AllArgsConstructor;

/**
 * Mapper class for mapping between Comment entities and DTOs.
 */
@Component
@AllArgsConstructor
public class CommentMapper {

	/**
	 * Mapper for mapping between activity entities and DTOs.
	 */
	private final ActivityMapper activityMapper;
	/**
	 * Mapper for mapping between user entities and DTOs.
	 */
	private final UserMapper userMapper;

	/**
	 * Converts a CommentDTO object to a Comment object.
	 *
	 * @param commentDTO The CommentDTO object to be converted.
	 * @return The corresponding Comment object.
	 */
	public Comment commentDtoToComment(CommentDTO commentDTO) {
		if (commentDTO == null) {
			return null;
		} else {
			Comment comment = new Comment();
			comment.setContent(commentDTO.getContent());
			comment.setActivity(activityMapper.activityDTOtoActivity(commentDTO.getActivity()));
			comment.setUser(userMapper.userDTOtoUser(commentDTO.getUser()));
			return comment;
		}
	}

	/**
	 * Converts a Comment object to a CommentDTO object.
	 *
	 * @param comment The Comment object to be converted.
	 * @return The corresponding CommentDTO object.
	 */
	public CommentDTO commentToCommentDTO(Comment comment) {
		if (comment == null) {
			return null;
		} else {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setContent(comment.getContent());
			commentDTO.setUser(userMapper.userToUserDTO(comment.getUser()));
			commentDTO.setActivity(activityMapper.activityToActivityDTO(comment.getActivity()));
			return commentDTO;
		}
	}
}