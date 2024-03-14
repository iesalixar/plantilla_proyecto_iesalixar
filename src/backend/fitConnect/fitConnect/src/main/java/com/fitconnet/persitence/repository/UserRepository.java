package com.fitconnet.persitence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

@Repository
public interface UserRepository extends MyBaseRepository<User, Long> {

	User findByEmail(String email);

	List<User> findByLastName(String lastName);

	List<User> findByActivitiesPlace(String place);

	List<User> findByRoles(Role role);

	List<User> findByActivitiesActivityType(String activityType);

}
