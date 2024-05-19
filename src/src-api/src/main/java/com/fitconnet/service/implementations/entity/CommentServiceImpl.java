package com.fitconnet.service.implementations.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.CommentDTO;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.repository.CommentRepository;
import com.fitconnet.service.interfaces.entity.CommentServiceI;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentServiceI {

	private final CommentRepository commentRepo;

	@Override
	public Comment getById(Long id) {
		return commentRepo.findById(id).get();
	}

	@Override
	public List<Comment> getAll() {
		return commentRepo.findAll();
	}

	@Override
	public boolean existById(Long id) {
		return commentRepo.existsById(id);
	}

	@Override
	public void create(Comment comment) {
		commentRepo.save(comment);
	}

	@Override
	public void update(Long id, Comment comment) {

		if (existById(id)) {
			Comment aux = commentRepo.findById(id).get();
			commentRepo.delete(aux);
			commentRepo.save(comment);
		}

	}

	@Override
	public void delete(Long id) {
		commentRepo.deleteById(id);
	}

	@Override
	public Comment commentDtoToComment(CommentDTO dto) {
		Comment comment = new Comment();
		comment.setContent(dto.getContent());
		comment.setActivity(dto.getActivity());
		comment.setUser(dto.getUser());
		return comment;
	}

}
