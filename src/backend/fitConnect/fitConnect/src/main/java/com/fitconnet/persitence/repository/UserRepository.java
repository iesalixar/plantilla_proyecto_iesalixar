package com.fitconnet.persitence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

@Repository
public interface UserRepository extends MyBaseRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<List<User>> findByLastName(String lastName);

	Optional<List<User>> findByActivitiesPlace(String place);

	Optional<List<User>> findByRoles(Role role);

	Optional<List<User>> findByActivitiesActivityType(String activityType);

}
