package com.fitconnet.dto.entities;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an activity.
 */
@Data
public class ActivityDTO {
	/**
	 * The unique identifier for the activity.
	 */
	private Long id;
	/**
	 * The title of activity.
	 */
	private String title;
	/**
	 * The type of activity.
	 */
	private String type;

	/**
	 * The duration of the activity.
	 */
	private String duration;

	/**
	 * The place where the activity takes place.
	 */
	private String place;

	/**
	 * The date of the activity.
	 */
	private Date date;

	/**
	 * The creator of the activity.
	 */
	private UserDTO creator;

	/**
	 * The image of the activity.
	 */
	private String image;
	/**
	 * The participants of the activity.
	 */
	private List<UserDTO> participants;
}