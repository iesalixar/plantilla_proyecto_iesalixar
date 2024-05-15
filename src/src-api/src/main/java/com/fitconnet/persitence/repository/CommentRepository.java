package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitconnet.persitence.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
