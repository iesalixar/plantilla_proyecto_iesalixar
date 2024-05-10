package com.fitconnet.persitence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUserName(String userName);

	Optional<List<User>> findByLastName(String lastName);

	Optional<List<User>> findByRoles(Role role);

	Boolean existsByEmail(String email);
}
