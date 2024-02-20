package ru.DreamyUndertaker.Waveaccess.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.DreamyUndertaker.Waveaccess.models.Person;
import ru.DreamyUndertaker.Waveaccess.repositories.PeopleRepository;

@Service
public class RegistrationService {
	private final PeopleRepository peopleRepository;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
		this.peopleRepository = peopleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void registerPeople(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		peopleRepository.save(person);
	}
}
