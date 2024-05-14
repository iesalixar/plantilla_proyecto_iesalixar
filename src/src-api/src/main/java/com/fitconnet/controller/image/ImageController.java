package com.fitconnet.controller.image;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fitconnet.dto.response.ErrorDetailsDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Image;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.ImageServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;

/**
 * REST Controller for managing image operations.
 */

@RestController
@RequestMapping("/api/v1/image")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class ImageController {
	/**
	 * Dependency injection for the ImageServiceI interface.
	 */
	@Qualifier("imageService")
	private final ImageServiceI imageService;
	/**
	 * Dependency injection for the ImageServiceI interface.
	 */
	@Qualifier("imageService")
	private final ActivityServiceI activityService;
	/**
	 * Dependency injection for the ProcessingResponseI interface.
	 */
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;
	/**
	 * Dependency injection for the GlobalExceptionHandler.
	 */
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;
	/**
	 * Logger instance for AdminController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(ImageController.class);

	/**
	 * Creates a new image.
	 *
	 * @param file The image file to upload.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the creation operation.
	 * @throws SerialException If a database access error occurs.
	 * @throws SQLException    If a database access error occurs.
	 * @throws IOException     If an I/O error occurs.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Create Image", description = "Uploads a new image.")
	@ApiResponse(responseCode = "200", description = "Image uploaded successfully")
	public ResponseEntity<String> createImage(
			@Parameter(description = "Image file to upload", required = true) @RequestParam("image") MultipartFile file,
			@Parameter(description = "ID associated with the activity", required = true) @RequestParam("id") Long activityId)
			throws SerialException, SQLException, IOException {
		logger.info("ImageController :: createImage");
		ResponseEntity<String> response = null;
		Boolean exist = activityService.existById(activityId);
		if (!exist) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.IMAGE_NOT_FOUND);
		} else {
			// Get image info
			byte[] bytes = file.getBytes();
			// Casting to datatype to be store
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			// Create a new image and setting valeues
			Image image = new Image();
			image.setImage(blob);
			image.setActivity(activityService.getOne(activityId));
			imageService.create(image);

			response = ResponseEntity.ok().body("Image uploaded successfully");
		}
		return response;

	}

	/**
	 * Retrieves all images.
	 *
	 * @return ResponseEntity<List<byte[]>> The response entity containing the list
	 *         of images.
	 * @throws SQLException If a database access error occurs.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Get All Images", description = "Retrieves all images.")
	@ApiResponse(responseCode = "200", description = "Images retrieved successfully")
	public ResponseEntity<List<byte[]>> showImages() throws SQLException {
		logger.info("ImageController :: getAllImages");
		List<Image> images = imageService.getAll();
		List<byte[]> imageBytesList = new ArrayList<>();
		for (Image image : images) {
			byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
			imageBytesList.add(imageBytes);
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytesList);
	}

	/**
	 * Retrieves an image by its ID.
	 *
	 * @param id The ID of the image to retrieve.
	 * @return ResponseEntity<byte[]> The response entity containing the image.
	 * @throws SQLException If a database access error occurs.
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Get Image by ID", description = "Retrieves an image by its ID.")
	@ApiResponse(responseCode = "200", description = "Image retrieved successfully")
	public ResponseEntity<byte[]> getImageById(
			@Parameter(description = "ID of the image to retrieve", required = true) @PathVariable Long id)
			throws SQLException {
		logger.info("ImageController :: getImageById");
		Image image = imageService.getById(id);
		byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	/**
	 * Deletes an image by its ID.
	 *
	 * @param id The ID of the image to delete.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #id == authentication.principal.id")
	@Operation(summary = "Delete Image", description = "Deletes an image by its ID.")
	@ApiResponse(responseCode = "200", description = "Image deleted successfully")
	public ResponseEntity<String> deleteImage(
			@Parameter(description = "ID of the image to delete", required = true) @PathVariable Long id) {
		logger.info("ImageController :: deleteUser");
		ResponseEntity<String> response = null;
		Boolean exist = imageService.existById(id);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.IMAGE_NOT_FOUND), () -> {
					imageService.delete(id);
					return ResponseEntity.ok().body("Image has been deleted successfully.");
				});
		return response;
	}

	/**
	 * Updates an image by replacing it with another.
	 *
	 * @param id   The ID of the image to update.
	 * @param file The new image file.
	 * @return ResponseEntity<String> The response entity indicating the success or
	 *         failure of the update operation.
	 * @throws IOException     If an I/O error occurs.
	 * @throws SerialException If a database access error occurs.
	 * @throws SQLException    If a database access error occurs.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Update Image", description = "Updates an image by replacing it with another.")
	@ApiResponse(responseCode = "200", description = "Image updated successfully")
	public ResponseEntity<String> updateImage(
			@Parameter(description = "ID of the image to update", required = true) @PathVariable Long id,
			@Parameter(description = "New image file", required = true) @RequestParam("image") MultipartFile file)
			throws IOException, SerialException, SQLException {
		logger.info("ImageController :: updateImage");
		ResponseEntity<String> response = null;
		Boolean exist = imageService.existById(id);
		Image newImage = new Image();
		byte[] bytes = file.getBytes();
		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		newImage.setImage(blob);
		response = processingResponseI.processStringResponse(exist,
				() -> ResponseEntity.status(HttpStatus.CONFLICT).body(Constants.IMAGE_NOT_FOUND), () -> {
					imageService.update(id, newImage);
					return ResponseEntity.ok().body("Image has been updated successfully.");
				});
		return response;
	}

	/**
	 * Handles NoHandlerFoundException.
	 * 
	 * <p>
	 * Handles the case when no handler is found for a request.
	 * </p>
	 * 
	 * @param ex      The NoHandlerFoundException instance.
	 * @param request The web request.
	 * @return ResponseEntity<ErrorDetailsResponse> The response entity containing
	 *         details of the error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleException(Exception ex, WebRequest request) {
		return globalExceptionHandler.handleCommonExceptions(ex, request);
	}
}
