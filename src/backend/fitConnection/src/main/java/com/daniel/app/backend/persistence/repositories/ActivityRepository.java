package com.daniel.app.backend.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.app.backend.persistence.model.Activity;
import com.daniel.app.backend.persistence.model.User;

/**
 * Repositorio JPA para la entidad {@link Activity}. Proporciona métodos para
 * realizar operaciones de persistencia relacionadas con las actividades.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	/**
	 * Recupera una lista de actividades asociadas a un usuario específico.
	 *
	 * @param user El usuario del cual se quieren recuperar las actividades.
	 * @return Una lista de actividades asociadas al usuario proporcionado.
	 */
	List<Activity> findByUser(User user);

	/**
	 * Recupera un usuario por su nombre de usuario.
	 *
	 * @param activityType El nombre de usuario del usuario a buscar.
	 * @return El usuario que coincide con el nombre de usuario proporcionado, o
	 *         null si no se encuentra.
	 */
	Activity findByActivityType(String activityType);
}