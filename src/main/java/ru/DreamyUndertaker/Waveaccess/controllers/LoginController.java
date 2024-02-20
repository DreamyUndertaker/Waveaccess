package ru.DreamyUndertaker.Waveaccess.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.DreamyUndertaker.Waveaccess.models.Person;
import ru.DreamyUndertaker.Waveaccess.services.RegistrationService;
import ru.DreamyUndertaker.Waveaccess.util.PersonValidator;
@Controller
@RequestMapping("/auth")
public class LoginController {
	private final PersonValidator personValidator;
	private final RegistrationService registrationService;
	@Autowired
	public LoginController(PersonValidator personValidator, RegistrationService registrationService) {
		this.personValidator = personValidator;
		this.registrationService = registrationService;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			request.getSession().invalidate();
		}
		return "redirect:auth/login";
	}

	@GetMapping("/login")
	public String login(){
		return "auth/login";
	}
	@GetMapping("/registration")
	public String registration(@ModelAttribute("person") Person person){
		return "auth/registration";
	}

	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult result){
		personValidator.validate(person, result);

		if (result.hasErrors()){
			return "auth/registration";
		}

		registrationService.registerPeople(person);

		return "redirect:/login";
	}
}
