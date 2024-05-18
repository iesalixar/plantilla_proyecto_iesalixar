package com.fitconnet.dto.requets;

import java.util.Date;

import com.fitconnet.persitence.model.Activity;

import lombok.Data;

@Data
public class PublicationDTO {
	/**
	 * The title of the publication.
	 */
	private String title;

	/**
	 * The date of the activity.
	 */
	private Date date;

	/**
	 * The activity associated with the publication.
	 */
	private Activity activity;
}
