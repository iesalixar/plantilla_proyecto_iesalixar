package com.daniel.app.backend.utilities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daniel.app.backend.dtos.UserDTO;
import com.daniel.app.backend.persistence.model.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "name", target = "fullName")
	@Mapping(source = "user", target = "userName")
	@Mapping(source = "password", target = "password")
	@Mapping(source = "email", target = "email")
	UserDTO map(User user);

}
