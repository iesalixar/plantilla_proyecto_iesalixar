package com.fitconnet.service.interfaces.entity;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

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
	 * @return An optional containing a set of all activities, if any.
	 */
	Optional<Set<Activity>> getAll();

	/**
	 * Retrieves an activity by its ID.
	 *
	 * @param id The ID of the activity to retrieve.
	 * @return An optional containing the activity with the specified ID, if found.
	 */
	Optional<Activity> getOne(Long id);

	/**
	 * Checks if an activity exists by its ID.
	 *
	 * @param id The ID of the activity to check.
	 * @return true if an activity exists with the specified ID, false otherwise.
	 */
	Boolean existById(Long id);

	/**
	 * Checks if an activity exists for a given date.
	 *
	 * @param date The date to check.
	 * @return true if an activity exists for the given date, false otherwise.
	 */
	Boolean existByDate(Date date);

	/**
	 * Converts an activity entity to an activity DTO.
	 *
	 * @param activity The activity entity to convert.
	 * @param userDTO  The user DTO associated with the activity.
	 * @return The activity DTO corresponding to the activity entity.
	 */
	ActivityDTO activityToActivityDTO(Activity activity, UserDTO userDTO);

	/**
	 * Converts an activity DTO to an activity entity.
	 *
	 * @param activityDTO The activity DTO to convert.
	 * @param user        The user entity associated with the activity.
	 * @return The activity entity corresponding to the activity DTO.
	 */
	Activity activityDTOtoActivity(ActivityDTO activityDTO, User user);

	/**
	 * Converts participant DTOs to participant entities.
	 *
	 * @param participantsDTO The set of participant DTOs to convert.
	 * @return The set of participant entities corresponding to the participant
	 *         DTOs.
	 */
	Set<User> participantsDTOtoParticipants(Set<UserDTO> participantsDTO);

	/**
	 * Converts participant entities to participant DTOs.
	 *
	 * @param participants The set of participant entities to convert.
	 * @return The set of participant DTOs corresponding to the participant
	 *         entities.
	 */
	Set<UserDTO> participantstoParticipantsDTO(Set<User> participants);

	/**
	 * Creates a new activity.
	 *
	 * @param activity The activity to create.
	 */
	void create(Activity activity);

	/**
	 * Updates an existing activity.
	 *
	 * @param id       The ID of the activity to update.
	 * @param activity The updated activity.
	 */
	void update(Long id, Activity activity);

	/**
	 * Partially updates an existing activity.
	 *
	 * @param id       The ID of the activity to update.
	 * @param activity The partial update for the activity.
	 */
	void patch(Long id, Activity activity);

	/**
	 * Deletes an activity by its ID.
	 *
	 * @param id The ID of the activity to delete.
	 */
	void deleteById(Long id);
}