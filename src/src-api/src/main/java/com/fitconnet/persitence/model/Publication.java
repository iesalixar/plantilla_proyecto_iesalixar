package com.fitconnet.persitence.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an PUBLICATION entity.
 */
@Entity
@Table(name = "T_PUBLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
	private static final long serialVersionUID = 1L;

	/**
	 * The unique identifier for the publication.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_PUBLICATION_ID", unique = true, nullable = false)
	private Long id;

	/**
	 * The title of the publication.
	 */
	@Column(name = "C_PUBLICATION_TITLE", nullable = false)
	private String title;

	/**
	 * The list of comments associated with the publication.
	 */
	@OneToMany(mappedBy = "publication")
	private List<Comment> comments;

	/**
	 * The activity associated with the publication.
	 */
	@OneToOne
	@JoinColumn(name = "C_PK_ACTIVITY_ID")
	private Activity activity;

}
