package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.persitence.model.Publication;

/**
 * Interface for the Publication Service.
 */
public interface PublicationServiceI {

	/**
	 * Retrieves a publication by its unique identifier.
	 *
	 * @param id the unique identifier of the publication
	 * @return the publication with the specified id
	 */
	Publication getById(Long id);

	/**
	 * Retrieves all publications.
	 *
	 * @return a list of all publications
	 */
	List<Publication> getAll();

	/**
	 * Checks if a publication exists by its unique identifier.
	 *
	 * @param id the unique identifier of the publication
	 * @return true if the publication exists, false otherwise
	 */
	boolean existById(Long id);

	/**
	 * Creates a new publication.
	 *
	 * @param publication the publication to create
	 */
	void create(Publication publication);

	/**
	 * Updates an existing publication.
	 *
	 * @param id             the unique identifier of the publication to update
	 * @param newPublication the updated publication details
	 */
	void update(Long id, Publication newPublication);

	/**
	 * Deletes a publication by its unique identifier.
	 *
	 * @param id the unique identifier of the publication to delete
	 */
	void delete(Long id);
}
