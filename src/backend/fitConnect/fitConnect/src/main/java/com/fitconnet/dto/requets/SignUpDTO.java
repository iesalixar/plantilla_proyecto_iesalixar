package com.fitconnet.dto.requets;

import lombok.Data;

@Data
public class SignUpDTO {
	private String firstName;
	private String lastName;
	private String userName;
	private Integer age;
	private String email;
	private String password;

}
