package com.daniel.app.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.app.backend.persistence.model.User;

/**
 * Repositorio JPA para la entidad {@link User}. Proporciona métodos para
 * realizar operaciones de persistencia relacionadas con los usuarios.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Recupera un usuario por su nombre de usuario.
	 *
	 * @param userName El nombre de usuario del usuario a buscar.
	 * @return El usuario que coincide con el nombre de usuario proporcionado, o
	 *         null si no se encuentra.
	 */
	User findByUserName(String userName);
}
