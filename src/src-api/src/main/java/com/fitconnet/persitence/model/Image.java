package com.fitconnet.persitence.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an activity entity.
 */
@Entity
@Table(name = "T_IMAGE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	private static final long serialVersionUID = 1L;
	/**
	 * The unique identifier for the image.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_IMAGE_ID", unique = true, nullable = false)
	private Long id;
	/**
	 * Represents an image entity stored in the database.
	 */
	@Lob
	@Column(name = "C_IMAGE_BLOB", unique = true, nullable = false)
	private Blob image;

	/**
	 * The activity associated with the image.
	 */
	@OneToOne(mappedBy = "image")
	private Activity activity;
}
