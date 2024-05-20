package com.fitconnet.dto.entities;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an activity.
 */
@Data
public class ActivityDTO {
	/**
	 * The type of activity.
	 */
	private String type;

	/**
	 * The duration of the activity.
	 */
	private Duration duration;

	/**
	 * The place where the activity takes place.
	 */
	private String place;

	/**
	 * The date of the activity.
	 */
	private Date date;

	/**
	 * The likes of the activity.
	 */
	private Integer likes;

	/**
	 * The creator of the activity.
	 */
	private UserDTO creator;

	/**
	 * The image of the activity.
	 */
	private ImageDTO activityImg;

	/**
	 * The participants of the activity.
	 */
	private List<UserDTO> participants;
}