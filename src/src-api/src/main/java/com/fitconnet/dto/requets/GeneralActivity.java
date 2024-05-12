package com.fitconnet.dto.requets;

import com.fitconnet.dto.entities.ActivityDTO;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a general activity with user
 * association.
 */
@Data
public class GeneralActivity {
	/**
	 * The activity details.
	 */
	private ActivityDTO activity;

	/**
	 * The ID of the user associated with the activity.
	 */
	private Long userId;
}