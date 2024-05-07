package com.fitconnet.persitence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUserName(String userName);

//	Optional<List<User>> findByLastName(String lastName);

//	Optional<List<User>> findByActivitiesPlace(String place);

//	Optional<List<User>> findByRoles(Role role);

//	Optional<List<User>> findByActivitiesActivityType(String activityType);

	Boolean existsByEmail(String email);
}
