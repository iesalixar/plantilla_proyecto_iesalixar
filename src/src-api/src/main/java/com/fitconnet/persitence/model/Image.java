package com.fitconnet.persitence.model;

import java.sql.Blob;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	/**
	 * The unique identifier for the image.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Represents an image entity stored in the database.
	 */
	@Lob
	private Blob image;

	/**
	 * The activity associated with the image.
	 */
	@OneToOne(mappedBy = "image")
	private Activity activity;
}
