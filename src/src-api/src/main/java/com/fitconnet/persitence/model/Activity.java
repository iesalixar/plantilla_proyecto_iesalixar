package com.fitconnet.persitence.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an activity entity.
 */
@Entity
@Table(name = "T_ACTIVITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class  Activity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	/**
	 * The unique identifier for the activity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_ACTIVITY_ID", unique = true, nullable = false)
	private Long id;
	/**
	 * The title of the publication.
	 */
	@Column(name = "C_ACTIVITY_TITLE", nullable = false)
	private String title;
	/**
	 * The type of the activity.
	 */
	@Column(name = "C_ACTIVITY_TYPE", length = 30, nullable = false)
	private String type;

	/**
	 * The duration of the activity.
	 */
	@Column(name = "C_ACTIVITY_DURATION", nullable = false)
	private String duration;

	/**
	 * The place where the activity takes place.
	 */
	@Column(name = "C_ACTIVITY_PLACE", length = 100)
	private String place;

	/**
	 * The date of the activity.
	 */
	@Column(name = "C_DATE", nullable = false)
	private Date date;

	/**
	 * The profile picture.
	 */
	@Column(name = "C_ACTIVITY_IMAGE", columnDefinition = "LONGTEXT")
	private String image;
	/**
	 * The creator of the activity.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "C_ACTIVITY_CREATOR")
	@JsonBackReference
	private User creator;

	/**
	 * The participants of the activity.
	 */
	@ManyToMany
	@JoinTable(name = "T_ACTIVITY_PARTICIPANTS", joinColumns = @JoinColumn(name = "C_PK_ACTIVITY_ID"), inverseJoinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	private List<User> participants;

}
