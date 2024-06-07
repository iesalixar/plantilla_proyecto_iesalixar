package com.fitconnet.service.implementations.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.repository.CommentRepository;
import com.fitconnet.service.interfaces.entity.CommentServiceI;
import com.fitconnet.utils.mappers.CommentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentServiceI {
	/**
	 * Repository for comment-related operations.
	 */
	private final CommentRepository commentRepository;

	/**
	 * Mapper for mapping between comment entities and DTOs.
	 */
	private final CommentMapper commentMapper;

	@Override
	public CommentDTO getById(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if (optionalComment.isPresent()) {
			return commentMapper.commentToCommentDTO(optionalComment.get());
		} else {
			return null;
		}
	}

//	@Override
//	public List<CommentDTO> getComments(UserDTO user) {
//		return user.getComments();
//	}

	@Override
	public List<CommentDTO> getAll() {
		return commentRepository.findAll().stream().map(commentMapper::commentToCommentDTO).toList();
	}

	@Override
	public boolean existById(Long id) {
		return commentRepository.existsById(id);
	}

	@Override
	public void create(CommentDTO commentDTO) {
		commentRepository.save(commentMapper.commentDtoToComment(commentDTO));
	}

	@Override
	public void update(Long id, CommentDTO commentDTO) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if (optionalComment.isPresent()) {
			Comment existingComment = optionalComment.get();
			commentRepository.delete(existingComment);
			commentRepository.save(commentMapper.commentDtoToComment(commentDTO));
		}
	}

	@Override
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}

}
