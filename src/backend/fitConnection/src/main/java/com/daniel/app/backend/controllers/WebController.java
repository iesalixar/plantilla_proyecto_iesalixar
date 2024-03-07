package com.daniel.app.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "fragments/login";
	}

	@GetMapping("/register")
	public String showRegisterForm() {
		return "fragments/register";
	}
}
