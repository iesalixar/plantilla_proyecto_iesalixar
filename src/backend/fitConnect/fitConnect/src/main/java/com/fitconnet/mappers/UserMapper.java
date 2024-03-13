package com.fitconnet.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fitconnet.dto.UserDTO;
import com.fitconnet.persitence.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "email", target = "email")
	UserDTO userToUserDTO(User user);

	User userDTOToUser(UserDTO userDTO);

	List<UserDTO> toUserDTOS(Iterable<User> iterable);

	void updateUser(@MappingTarget User target, User source);

}
