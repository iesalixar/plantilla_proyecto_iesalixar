package com.daniel.app.backend.services.interfaces;

import java.util.List;

import com.daniel.app.backend.dtos.UserDTO;
import com.daniel.app.backend.persistence.model.User;

/**
 * Interfaz que define los servicios relacionados con los usuarios.
 */
public interface UserServiceI {

	/**
	 * Recupera todos los usuarios.
	 *
	 * @return Lista de {@link UserDTO} que representa todos los usuarios.
	 */
	List<UserDTO> getAllUsers();

	/**
	 * Recupera un usuario por su identificador único.
	 *
	 * @param id Identificador único del usuario a recuperar.
	 * @return {@link UserDTO} que representa el usuario con el identificador
	 *         proporcionado.
	 */
	User getUserById(Long id);

	/**
	 * Recupera un usuario por su nombre de usuario.
	 *
	 * @param userName El nombre de usuario del usuario a buscar.
	 * @return {@link UserDTO} que representa el usuario con el nombre de usuario
	 *         proporcionado.
	 */
	User getUserByUserName(String userName);

	/**
	 * Guarda un nuevo usuario.
	 *
	 * @param userDTO El usuario a guardar.
	 */
	void saveUser(User user);

	/**
	 * Actualiza un usuario existente.
	 *
	 * @param userDTO El usuario a actualizar.
	 */
	void updateUser(User user);

	/**
	 * Elimina un usuario por su identificador único.
	 *
	 * @param id Identificador único del usuario a eliminar.
	 */
	void deleteUser(Long id);

	boolean existUser(String userName);
}
