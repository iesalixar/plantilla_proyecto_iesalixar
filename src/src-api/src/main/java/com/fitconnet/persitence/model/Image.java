package com.fitconnet.persitence.model;

import org.springframework.data.annotation.Id;

import com.mysql.cj.jdbc.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an activity entity.
 */
@Entity
@Table(name = "T_IMAGE")
@Data
@AllArgsConstructor
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
}
