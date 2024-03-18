package com.fitconnet.persitence.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.NoArgsConstructor;

/**
 * Clase que representa una actividad en el sistema. Esta entidad mapea a la
 * tabla T_ACTIVITY en la base de datos.
 */
@Entity
@Table(name = "T_ACTIVITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** Identificador único de la actividad. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_ACTIVITY_ID", unique = true, nullable = false)
	private Long id; // Corregido el nombre

	/** Tipo de actividad (por ejemplo, correr, nadar, etc.). */
	@Column(name = "C_ACTIVITY_TYPE", length = 30, nullable = false)
	private String type;

	/** Tiempo de duración en horas y/o minutos */
	@Column(name = "C_ACTIVITY_DURATION", length = 8, nullable = false)
	private Duration duration;

	/** Lugar asociado a esta actividad. */
	@Column(name = "C_ACTIVITY_PLACE", length = 100)
	private String place;

	/** Usuario asociado a esta actividad. */
	@ManyToOne
	@JoinColumn(name = "C_ACTIVITY_CREATOR", referencedColumnName = "C_PK_USER_ID")
	private User creator;

	@ManyToMany
	@JoinTable(name = "T_ACTIVITY_PARTICIPANTS", joinColumns = @JoinColumn(name = "C_PK_ACTIVITY_ID"), inverseJoinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	private Set<User> participants;

}
