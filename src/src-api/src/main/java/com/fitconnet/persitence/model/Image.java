package com.fitconnet.persitence.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an activity entity.
 */
@Entity
@Table(name = "T_IMAGE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "C_IMAGE_TYPE", discriminatorType = DiscriminatorType.STRING)
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

}
