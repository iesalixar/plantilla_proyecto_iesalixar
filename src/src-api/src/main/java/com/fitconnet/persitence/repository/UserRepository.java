package com.fitconnet.persitence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

/**
 * Repository interface for managing users.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by email.
	 *
	 * @param email The email of the user to find.
	 * @return An optional containing the user with the specified email, if found.
	 */
	User findByEmail(String email);

	/**
	 * Finds users by roles.
	 *
	 * @param role The role of the users to find.
	 * @return An optional containing a list of users with the specified role.
	 */
	Optional<List<User>> findByRoles(Role role);

	/**
	 * Checks if a user exists by email.
	 *
	 * @param email The email to check.
	 * @return true if a user exists with the specified email, false otherwise.
	 */
	Boolean existsByEmail(String email);

}