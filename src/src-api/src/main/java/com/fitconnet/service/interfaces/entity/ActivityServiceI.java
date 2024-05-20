package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.List;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.persitence.model.Activity;

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
	boolean existByDate(Date date);

	/**
	 * Converts an activity entity to an activity DTO.
	 *
	 * @param activity The activity entity to convert.
	 * @param userDTO  The user DTO associated with the activity.
	 * @return The activity DTO corresponding to the activity entity.
	 */
	ActivityDTO activityToActivityDTO(Activity activity);

	/**
	 * Converts an activity DTO to an activity entity.
	 *
	 * @param activityDTO The activity DTO to convert.
	 * @param user        The user entity associated with the activity.
	 * @return The activity entity corresponding to the activity DTO.
	 */
	Activity activityDTOtoActivity(ActivityDTO activityDTO);

	/**
	 * Retrieves the activities created by a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of activities created by the specified user.
	 */
	List<ActivityDTO> getCreatedActivities(Long id);

	/**
	 * Retrieves the activities invited to by a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of activities invited to by the specified user.
	 */
	List<ActivityDTO> getInvitedActivities(Long id);

	/**
	 * Retrieves all activities related to a user.
	 * 
	 * @param id The id of the user.
	 * @return A list of all activities related to the specified user.
	 */
	List<ActivityDTO> getAllActivities(Long id);

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
