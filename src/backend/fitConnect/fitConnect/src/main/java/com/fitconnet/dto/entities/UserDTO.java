package com.fitconnet.dto.entities;

import java.util.Set;

import com.fitconnet.enums.Role;

import lombok.Data;

@Data
public class UserDTO {
	private String firstName;
	private String lastName;
	private String userName;
	private Integer age;
	private String email;
	private String password;
	private Set<Role> roles;
}
