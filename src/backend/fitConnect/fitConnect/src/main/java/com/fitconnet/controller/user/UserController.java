package com.fitconnet.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Qualifier("userService")
	private final UserServiceI userService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserServiceI userService) {
		this.userService = userService;
	}

}
