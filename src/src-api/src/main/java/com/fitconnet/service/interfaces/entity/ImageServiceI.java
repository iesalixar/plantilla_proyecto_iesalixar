package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.dto.entities.ImageDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.ActivityImg;
import com.fitconnet.persitence.model.Image;
import com.fitconnet.persitence.model.ProfileImg;
import com.fitconnet.persitence.model.User;

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

	ImageDTO imageToImageDTO(Image img);

	Image imageDTOtoImage(ImageDTO dto);

	/**
	 * Converts an ImageDTO to an ActivityImg entity.
	 *
	 * @param imageDTO The ImageDTO to convert.
	 * @param activity The Activity entity to associate with the image.
	 * @return The resulting ActivityImg entity.
	 */
	ActivityImg imageDTOToActivityImg(ImageDTO imageDTO, Activity activity);

	/**
	 * Converts an ActivityImg entity to an ImageDTO.
	 *
	 * @param activityImg The ActivityImg entity to convert.
	 * @return The resulting ImageDTO.
	 */
	ImageDTO activityImgToImageDTO(ActivityImg activityImg);

	/**
	 * Converts an ImageDTO to a ProfileImg entity.
	 *
	 * @param imageDTO The ImageDTO to convert.
	 * @param user     The User entity to associate with the image.
	 * @return The resulting ProfileImg entity.
	 */
	ProfileImg imageDTOToProfileImg(ImageDTO imageDTO, User user);

	/**
	 * Converts a ProfileImg entity to an ImageDTO.
	 *
	 * @param profileImg The ProfileImg entity to convert.
	 * @return The resulting ImageDTO.
	 */
	ImageDTO profileImgToImageDTO(ProfileImg profileImg);

}