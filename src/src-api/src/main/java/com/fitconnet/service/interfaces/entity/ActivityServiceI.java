package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.dto.entities.ActivityDTO;

/**
 * Interface for managing activities.
 */
public interface ActivityServiceI {

	/**
	 * Retrieves all activities.
	 *
	 * @return A list of all activities.
	 */
	List<ActivityDTO> getAll();

	/**
	 * Retrieves an activity by its ID.
	 *
	 * @param id The ID of the activity to retrieve.
	 * @return The activity with the specified ID, if found.
	 */
	ActivityDTO getOne(Long id);

	/**
	 * Retrieves all activities related to a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of all activities related to the specified user.
	 */
	List<ActivityDTO> getAllActivitiesByUserId(Long id);

	/**
	 * Checks if an activity exists by its ID.
	 *
	 * @param id The ID of the activity to check.
	 * @return true if an activity exists with the specified ID, false otherwise.
	 */
	boolean existById(Long id);

	/**
	 * Checks if an activity exists for a given date.
	 *
	 * @param date The date to check.
	 * @return true if an activity exists for the given date, false otherwise.
	 */
	// boolean existByDate(Date date);

	/**
	 * Creates a new activity.
	 *
	 * @param activity The activity to create.
	 */
	void create(ActivityDTO activity);

	/**
	 * Updates an existing activity.
	 *
	 * @param id       The ID of the activity to update.
	 * @param activity The updated activity.
	 */
	void update(Long id, ActivityDTO activity);

	/**
	 * Partially updates an existing activity.
	 *
	 * @param id       The ID of the activity to update.
	 * @param activity The partial update for the activity.
	 */
	void patch(Long id, ActivityDTO activity);

	/**
	 * Deletes an activity by its ID.
	 *
	 * @param id The ID of the activity to delete.
	 */
	void deleteById(Long id);
}
