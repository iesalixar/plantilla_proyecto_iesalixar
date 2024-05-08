package com.fitconnet.persitence.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_USER")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
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
	@CollectionTable(name = "T_USER_ROLES", joinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	@Column(name = "C_USER_ROLES")
	private Set<Role> roles = new HashSet<>();

	/** Lista de actividades traqueadas por el usuario. */
	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private Set<Activity> createdActivities;

	@ManyToMany(mappedBy = "participants", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Activity> invitedActivities;

	@ManyToMany
	@JoinTable(name = "T_USER_FRIENDS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private Set<User> friends = new HashSet<>();

	@OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
	private Set<Notification> notifications = new LinkedHashSet<>();

	public User() {
	}

	@Transactional
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles.isEmpty()) {
			return null;
		} else {

			return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Activity> getCreatedActivities() {
		return createdActivities;
	}

	public void setCreatedActivities(Set<Activity> createdActivities) {
		this.createdActivities = createdActivities;
	}

	public Set<Activity> getInvitedActivities() {
		return invitedActivities;
	}

	public void setInvitedActivities(Set<Activity> invitedActivities) {
		this.invitedActivities = invitedActivities;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
