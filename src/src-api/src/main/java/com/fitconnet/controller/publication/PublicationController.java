package com.fitconnet.controller.publication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.dto.requets.PublicationDTO;
import com.fitconnet.error.GlobalExceptionHandler;
import com.fitconnet.persitence.model.Comment;
import com.fitconnet.persitence.model.Publication;
import com.fitconnet.service.interfaces.entity.ActivityServiceI;
import com.fitconnet.service.interfaces.entity.ProcessingResponseI;
import com.fitconnet.service.interfaces.entity.PublicationServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/publication")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class PublicationController {

	/**
	 * Dependency injection for the ActivityService interface.
	 */
	@Qualifier("activityService")
	private final ActivityServiceI activityService;

	/**
	 * Dependency injection for the UserService interface.
	 */
	@Qualifier("userService")
	private final PublicationServiceI publicationService;

	/**
	 * Dependency injection for the ProcessingResponse interface.
	 */
	@Qualifier("processingResponseI")
	private final ProcessingResponseI processingResponseI;

	/**
	 * Dependency injection for the GlobalExceptionHandler.
	 */
	@Qualifier("globalExceptionHandler")
	private final GlobalExceptionHandler globalExceptionHandler;

	/**
	 * Logger instance for ActivityController class.
	 */
	private final Logger logger = LoggerFactory.getLogger(PublicationController.class);

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@Operation(summary = "Create Publication", description = "Registers a new publication in the system.")
	@ApiResponse(responseCode = "200", description = "Publication created successfully")
	public ResponseEntity<String> createPublication(@RequestBody PublicationDTO request) {
		logger.info("ActivityController :: createActivity");
		ResponseEntity<String> response = null;
		List<Comment> comments = new ArrayList<>();
		Publication publication = new Publication();
		publication.setTitle(request.getTitle());
		publication.setDate(request.getDate());
		publication.setActivity(request.getActivity());
		publication.setComments(comments);
		publicationService.create(publication);

		return response;
	}

}
