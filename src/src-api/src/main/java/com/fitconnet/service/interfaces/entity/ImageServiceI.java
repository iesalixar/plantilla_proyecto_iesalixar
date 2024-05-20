package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.dto.entities.ImageDTO;

/**
 * Interface for managing images.
 */
public interface ImageServiceI {

	/**
	 * Retrieves all images.
	 *
	 * @return A list of all images.
	 */
	List<ImageDTO> getAll();

	/**
	 * Retrieves an image by its ID.
	 *
	 * @param id The ID of the image to retrieve.
	 * @return The image with the specified ID, if found.
	 */
	ImageDTO getById(Long id);

	/**
	 * Deletes an image by its ID.
	 *
	 * @param id The ID of the image to delete.
	 */
	void delete(Long id);

	/**
	 * Creates a new image.
	 *
	 * @param img The image to create.
	 */
	void create(ImageDTO img);

	/**
	 * Updates an existing image.
	 *
	 * @param id  The ID of the image to update.
	 * @param img The updated image.
	 */
	void update(Long id, ImageDTO img);

	/**
	 * Checks if an image exists by its ID.
	 *
	 * @param id The ID of the image to check.
	 * @return true if an image exists with the specified ID, false otherwise.
	 */
	boolean existById(Long id);

}
