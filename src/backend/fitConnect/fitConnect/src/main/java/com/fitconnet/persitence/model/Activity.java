package com.fitconnet.persitence.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@Column(name = "C_PK_ACTIVITY_ID")
	private Long idActivity; // Corregido el nombre

	/** Tipo de actividad (por ejemplo, correr, nadar, etc.). */
	@Column(name = "C_ACTIVITY_TYPE", length = 50)
	private String activityType;

	/** Tiempo de duración en horas y/o minutos */
	@Column(name = "C_ACTIVITY_DURATION", length = 8)
	private Duration duration;

	/** Distancia recorrida durante la actividad. */
	@Column(name = "C_DISTANCE")
	private double distance;

	/** Usuario asociado a esta actividad. */
	@ManyToOne
	@JoinColumn(name = "C_ACTIVITY_USER", referencedColumnName = "C_PK_ID_USER")
	private User user;

	/** Lugar asociado a esta actividad. */
	@Column(name = "C_ACTIVITY_PLACE")
	private String place;

	@ManyToMany(mappedBy = "C_ACTIVITY_PARTICIPANTS", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<User> participants;

}
