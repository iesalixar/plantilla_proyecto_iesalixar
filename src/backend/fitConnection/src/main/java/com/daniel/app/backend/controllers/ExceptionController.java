package com.daniel.app.backend.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String handleExceoption(HttpServletRequest req, Exception e, Model model) {

		model.addAttribute("error", e.getMessage());

		return "/error/error";
	}

}
