package com.fitconnet.persitence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an COMMENT entity.
 */
@Entity
@Table(name = "T_COMMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private static final long serialVersionUID = 1L;

	/**
	 * The unique identifier for the comment.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_COMMENT_ID", unique = true, nullable = false)
	private Long id;

	/**
	 * The content of the comment.
	 */
	@Column(name = "C_COMMENT_CONTENT", nullable = false)
	private String content;

	/**
	 * The user who created the comment.
	 */
	@ManyToOne
	@JoinColumn(name = "C_PK_USER_ID")
	private User user;

	/**
	 * The publication to which the comment belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "C_PK_PUBLICATION_ID")
	private Publication publication;

}
