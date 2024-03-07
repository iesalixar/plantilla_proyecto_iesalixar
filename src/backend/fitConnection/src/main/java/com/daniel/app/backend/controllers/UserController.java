package com.daniel.app.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daniel.app.backend.persistence.model.User;
import com.daniel.app.backend.services.interfaces.UserServiceI;
import com.daniel.app.backend.utilities.Constants;

@Controller
public class UserController {

	private final UserServiceI userService;

	public UserController(UserServiceI userService) {

		this.userService = userService;
	}

	private void setUserModelAttributes(User user, Model model) {

		model.addAttribute("user", user);

		model.addAttribute(Constants.ACTIVITIES_LIST, user.getActivities());
	}

	@PostMapping("/login")
	public String handleUserLoginForm(@RequestParam String userName, @RequestParam String password, Model model,
			RedirectAttributes redirectAttributes) {

		User existingUser = userService.getUserByUserName(userName);

		if (existingUser != null && existingUser.getPassword().equals(password)) {

			setUserModelAttributes(existingUser, model);

			return Constants.USER_PROFILE;

		} else {

			redirectAttributes.addFlashAttribute(Constants.USER_NAME, userName);

			return Constants.REGISTER_CONTROL;
		}
	}

	@PostMapping("/register")
	public String handleRegisterForm(@RequestParam String fullName, @RequestParam String email,
			@RequestParam String userName, @RequestParam String password, Model model) {

		User newUser = new User();
		newUser.setFullName(fullName);
		newUser.setEmail(email);
		newUser.setUserName(userName);
		newUser.setPassword(password);

		userService.saveUser(newUser);

		model.addAttribute(Constants.USER_ID, newUser.getIdUser());

		return "redirect:/userProfile?idUser=" + newUser.getIdUser();
	}

	@GetMapping("/userProfile")
	public String showUserProfile(@RequestParam Long idUser, Model model) {

		User user = userService.getUserById(idUser);

		System.out.println(user.getUserName());

		model.addAttribute(Constants.USER_ID, user.getIdUser());
		model.addAttribute(Constants.USER_NAME, user.getUserName());
		model.addAttribute(Constants.ACTIVITIES_LIST, user.getActivities());

		System.out.println(user.getIdUser());

		return "/users/profile";

	}

	@PostMapping("/viewDataUser")
	public String showDataUser(@RequestParam Long idUser, Model model) {

		User user = userService.getUserById(idUser);

		if (user != null) {

			model.addAttribute(Constants.USER_NAME, user.getUserName());
			model.addAttribute("fullName", user.getFullName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute(Constants.USER_ID, user.getIdUser());

			System.out.println(user.getIdUser());

		}
		return "/users/data";

	}

	@PostMapping("/deleteProfile")
	public String deleteUser(@RequestParam Long idUser, Model model) {

		userService.deleteUser(idUser);

		return "/index";
	}

	@PostMapping("/updateProfile")
	public String updateDataProfile(@RequestParam Long idUser, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String password, Model model) {

		User user = userService.getUserById(idUser);
		// Realiza la lógica de actualización en tu servicio

		if (user != null) {

			user.setFullName(fullName);
			user.setEmail(email);
			user.setPassword(password);

			userService.updateUser(user);

		}
		model.addAttribute(Constants.USER_ID, user.getIdUser());

		return "redirect:/viewDataUser";

	}

}
