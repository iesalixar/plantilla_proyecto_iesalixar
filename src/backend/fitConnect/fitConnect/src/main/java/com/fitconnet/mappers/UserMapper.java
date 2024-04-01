package com.fitconnet.mappers;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.fitconnet.dto.UserDTO;
import com.fitconnet.persitence.model.User;

@Mapper
public interface UserMapper {

	public UserDTO toUserDTO(User user);

	@InheritInverseConfiguration
	User toUser(UserDTO userDTO);


	void updateUser(User target, @MappingTarget User source);

	Set<User> toUserList(Set<UserDTO> participants);
	
	 default Set<UserDTO> toUserDTOList(Iterable<User> users) {
	        if (users == null) {
	            return null;
	        }

	        Set<UserDTO> userDTOs = new HashSet<>();
	        for (User user : users) {
	            UserDTO userDTO = toUserDTO(user);
	            userDTOs.add(userDTO);
	        }
	        return userDTOs;
	    }
}
