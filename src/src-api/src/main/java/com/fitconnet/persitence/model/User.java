package com.fitconnet.persitence.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an user entity.
 */
@Entity
@Table(name = "T_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * The unique identifier for the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_USER_ID", unique = true, nullable = false)
	private Long id;

	/**
	 * The first name of the user.
	 */
	@Column(name = "C_USER_NAME", nullable = false)
	@Size(min = 3, max = 20, message = "The name must be between 3 and 20 characters.")
	private String name;

	/**
	 * The age of the user.
	 */
	@Column(name = "C_USER_AGE", nullable = false)
	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 150, message = "Age should not be greater than 150")
	private Integer age;

	/**
	 * The email address of the user.
	 */
	@Column(name = "C_USER_EMAIL", unique = true, nullable = false)
	@Email(message = "Must be a valid e-mail address")
	private String email;

	/**
	 * The password of the user.
	 */
	@Column(name = "C_USER_PASSWORD")
	@Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters.")
	private String password;

	/**
	 * The profile picture.
	 */
	@Column(name = "C_USER_IMAGE", columnDefinition = "LONGTEXT")
	private String image;

	/**
	 * The roles assigned to the user.
	 */
	@ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "T_USER_ROLES", joinColumns = @JoinColumn(name = "C_PK_USER_ID"))
	@Column(name = "C_USER_ROLES")
	private Set<Role> roles = new HashSet<>();

	/**
	 * The friends of the user.
	 */
	@ManyToMany
	@JoinTable(name = "T_USER_FRIENDS", joinColumns = @JoinColumn(name = "C_USER_ID"), inverseJoinColumns = @JoinColumn(name = "C_FRIEND_ID"))
	private List<User> friends = new ArrayList<>();

	/**
	 * The notifications received by the user.
	 */
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private List<Notification> notifications = new ArrayList<>();

	/**
	 * Retrieves the authorities granted to the user.
	 *
	 * @return A collection of authorities granted to the user.
	 */
	@Transactional
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles.isEmpty()) {
			return null;
		} else {
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
		}
	}

	/**
	 * Retrieves the username used to authenticate the user.
	 *
	 * @return The username used to authenticate the user.
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * Indicates whether the user's account has expired.
	 *
	 * @return true if the user's account is valid (i.e., not expired), false
	 *         otherwise.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked.
	 *
	 * @return true if the user is not locked, false otherwise.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired.
	 *
	 * @return true if the user's credentials are valid (i.e., not expired), false
	 *         otherwise.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled.
	 *
	 * @return true if the user is enabled, false otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Retrieves the password used to authenticate the user.
	 *
	 * @return The password used to authenticate the user.
	 */
	@Override
	public String getPassword() {
		return password;
	}

}
