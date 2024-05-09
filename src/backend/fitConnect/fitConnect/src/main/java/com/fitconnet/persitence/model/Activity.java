package com.fitconnet.persitence.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
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

/**
 * Clase que representa una actividad en el sistema. Esta entidad mapea a la
 * tabla T_ACTIVITY en la base de datos.
 */
@Entity
@Table(name = "T_ACTIVITY")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Activity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** Identificador único de la actividad. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_ACTIVITY_ID", unique = true, nullable = false)
	private Long id;

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
	private User creator; // TODO cambiar para que se relacione por la id.

	@ManyToMany
	@JoinTable(name = "T_ACTIVITY_PARTICIPANTS", joinColumns = @JoinColumn(name = "C_PK_ACTIVITY_ID"), inverseJoinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	private Set<User> participants;
	// TODO poner nombre de la tabla
	private Date date;

	public void Activity() {
		// Método de construccion de actividades

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, date, duration, id, participants, place, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Activity other = (Activity) obj;
		return Objects.equals(creator, other.creator) && Objects.equals(date, other.date)
				&& Objects.equals(duration, other.duration) && Objects.equals(participants, other.participants)
				&& Objects.equals(place, other.place) && Objects.equals(type, other.type);
	}

}
