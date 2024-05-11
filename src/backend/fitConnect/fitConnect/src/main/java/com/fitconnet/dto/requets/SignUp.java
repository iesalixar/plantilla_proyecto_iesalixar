package com.fitconnet.dto.requets;

import lombok.Data;

@Data
public class SignUp {
	private String firstName;
	private String lastName;
	private String username;
	private Integer age;
	private String email;
	private String password;

}
