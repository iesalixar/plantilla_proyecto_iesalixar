package com.fitconnet.dto.entities;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

import lombok.Data;

@Data
public class CommentDTO {
	
	private String content;
	
	private User user;
	
	private Activity activity;

}
