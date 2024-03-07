package com.daniel.app.backend.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase que representa un usuario en el sistema. Esta entidad mapea a la tabla
 * T_USER en la base de datos.
 */
@Entity
@Table(name = "T_USER")
@Data
@AllArgsConstructor
public class User implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** Identificador único del usuario. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_ID_USER", unique = true, nullable = false)
	private Long idUser;

	/** Nombre completo del usuario. */
	@Column(name = "C_FULL_NAME", nullable = false)
	@Size(min = 3, max = 50, message = "El nombre completo debe tener entre 3 y 50 caracteres.")
	private String fullName;

	/** Nombre de usuario. */
	@Column(name = "C_USERNAME", unique = true, nullable = false)
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres.")
	private String userName;

	/** Contraseña del usuario. */
	@Column(name = "C_PASSWORD")
	@Size(min = 6, max = 30, message = "La contraseña debe tener entre 6 y 30 caracteres.")
	private String password;

	/** Cuenta de correo electrónico del usuario. */
	@Column(name = "C_EMAIL", unique = true, nullable = false)
	@Email(message = "Debe ser una dirección de correo electrónico válida")
	private String email;

	/** Lista de actividades traqueadas por el usuario. */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Activity> activities;

	public User() {
		this.activities = new ArrayList<>();
	}

}