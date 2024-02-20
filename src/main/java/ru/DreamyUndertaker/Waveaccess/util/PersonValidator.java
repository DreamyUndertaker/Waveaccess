package ru.DreamyUndertaker.Waveaccess.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.DreamyUndertaker.Waveaccess.models.Person;
import ru.DreamyUndertaker.Waveaccess.services.PersonDetailsService;
@Component
public class PersonValidator implements Validator {
	private final PersonDetailsService personDetailsService;
	@Autowired
	public PersonValidator(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Person.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;

		try {
			personDetailsService.loadUserByUsername(person.getUsername());
		} catch (UsernameNotFoundException ignored) {
			return;
		}

		errors.rejectValue("username", "error.person.notFound", "User was found");
	}
}
