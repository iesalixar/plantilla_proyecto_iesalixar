package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.List;

import com.fitconnet.dto.entities.ActivityDTO;
import com.fitconnet.dto.entities.UserDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

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
	 * Converts participant DTOs to participant entities.
	 *
	 * @param participantsDTO The list of participant DTOs to convert.
	 * @return The list of participant entities corresponding to the participant
	 *         DTOs.
	 */
	List<User> participantsDTOtoParticipants(List<UserDTO> participantsDTO);

	/**
	 * Converts participant entities to participant DTOs.
	 *
	 * @param participants The list of participant entities to convert.
	 * @return The list of participant DTOs corresponding to the participant
	 *         entities.
	 */
	List<UserDTO> participantstoParticipantsDTO(List<User> participants);

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
