package com.daniel.app.backend.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@Column(name = "C_PK_ID_ACTIVITY")
	private Long idActivity; // Corregido el nombre

	/** Tipo de actividad (por ejemplo, correr, nadar, etc.). */
	@Column(name = "C_ACTIVITY_TYPE", length = 50)
	private String activityType;

	/** Hora de inicio de la actividad. */
	@Column(name = "C_START_TIME")
	private LocalDateTime startTime;

	/** Hora de finalización de la actividad. */
	@Column(name = "C_END_TIME")
	private LocalDateTime endTime;

	/** Distancia recorrida durante la actividad. */
	@Column(name = "C_DISTANCE")
	private double distance;

	/** Usuario asociado a esta actividad. */
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "C_PK_ID_USER")
	private User user;

	/** Lugar asociado a esta actividad. */
	@Column(name = "PLACE_ID")
	private String place;

}