package com.fitconnet.service.implementations.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.repository.CommentRepository;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.CommentServiceI;
import com.fitconnet.service.interfaces.entity.UserServiceI;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentServiceI {

	private final CommentRepository commentRepo;
	private final UserServiceI userService;
	private final ActivityServiceI activityService;

	@Override
	public CommentDTO getById(Long id) {
		return commentToCommentDTO(commentRepo.findById(id).get());
	}

	@Override
	public List<CommentDTO> getAll() {
		return commentRepo.findAll().stream().map(this::commentToCommentDTO).toList();
	}

	@Override
	public boolean existById(Long id) {
		return commentRepo.existsById(id);
	}

	@Override
	public void create(CommentDTO commentDTO) {
		commentRepo.save(commentDtoToComment(commentDTO));
	}

	@Override
	public void update(Long id, CommentDTO commentDTO) {

		if (existById(id)) {
			Comment aux = commentRepo.findById(id).get();
			commentRepo.delete(aux);
			commentRepo.save(commentDtoToComment(commentDTO));
		}

	}

	@Override
	public void delete(Long id) {
		commentRepo.deleteById(id);
	}

	@Override
	public Comment commentDtoToComment(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setContent(commentDTO.getContent());
		comment.setActivity(activityService.activityDTOtoActivity(commentDTO.getActivity()));
		comment.setUser(userService.userDTOtoUser(commentDTO.getUser()));
		return comment;
	}

	@Override
	public CommentDTO commentToCommentDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setContent(comment.getContent());
		commentDTO.setUser(userService.userToUserDTO(comment.getUser()));
		commentDTO.setActivity(activityService.activityToActivityDTO(comment.getActivity()));
		return commentDTO;
	}

}
