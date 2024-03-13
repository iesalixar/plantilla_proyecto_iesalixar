package com.fitconnet.persitence.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fitconnet.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	/** Identificador único del usuario. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_USER_ID", unique = true, nullable = false)
	private Long id;
	/** Nombre completo del usuario. */
	@Column(name = "C_USER_FIRSTNAME", nullable = false)
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres.")
	private String firstName;
	@Column(name = "C_USER_LASTNAME")
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres.")
	private String lastName;
	/** Nombre de usuario. */
	@Column(name = "C_USER_USERNAME", unique = true, nullable = false)
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres.")
	private String userName;
	/** Email del usuario. */
	@Column(name = "C_USER_EMAIL", unique = true, nullable = false)
	@Email(message = "Debe ser una dirección de correo electrónico válida")
	private String email;
	/** Contraseña del usuario. */
	@Column(name = "C_USER_PASSWORD")
	@Size(min = 6, max = 30, message = "La contraseña debe tener entre 6 y 30 caracteres.")
	private String password;
	@ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "usuario_rol")
	@Column(name = "RolesUsuario")
	private Set<Role> roles = new HashSet<>();
	/** Lista de actividades traqueadas por el usuario. */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Activity> activities;

	@ManyToMany(mappedBy = "participants", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Activity> activitiesParticipated;

	@Transactional
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles.isEmpty()) {
			return null;
		} else {

			return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
		}
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

}
