package com.fitconnet.persitence.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The unique identifier for the activity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_ACTIVITY_ID", unique = true, nullable = false)
	private Long id;

	/**
	 * The type of the activity.
	 */
	@Column(name = "C_ACTIVITY_TYPE", length = 30, nullable = false)
	private String type;

	/**
	 * The duration of the activity.
	 */
	@Column(name = "C_ACTIVITY_DURATION", length = 8, nullable = false)
	private Duration duration;

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
	 * The image associated with the activity.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "C_IMG_ID", referencedColumnName = "id")
	private Image image;
	/**
	 * The creator of the activity.
	 */
	@ManyToOne
	@JoinColumn(name = "C_ACTIVITY_CREATOR", referencedColumnName = "C_PK_USER_ID")
	private User creator;

	/**
	 * The participants of the activity.
	 */
	@ManyToMany
	@JoinTable(name = "T_ACTIVITY_PARTICIPANTS", joinColumns = @JoinColumn(name = "C_PK_ACTIVITY_ID"), inverseJoinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	private Set<User> participants;
}
