package com.fitconnet.config;

import java.time.Duration;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.repository.ActivityRepository;
import com.github.javafaker.Faker;

@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

	static final boolean DELETE_ACTIVITY = true;

	// private final UserRepository userRepository;

	private final ActivityRepository activityRepository;

	// private final PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(InitializationData.class);

	public InitializationData(ActivityRepository activityRepository) {
		// this.userRepository = usuarioRepository;
		this.activityRepository = activityRepository;
		// this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {

		if (DELETE_ACTIVITY) {
			activityRepository.deleteAll();
		}

//		try {
//			// Usuario 1 - Rol USER
//			User usuario1 = new User();
//			usuario1.setFirstName("Alice");
//			usuario1.setLastName("Johnson");
//			usuario1.setEmail("alice.johnson@example.com");
//			usuario1.setPassword(passwordEncoder.encode("password123"));
//			usuario1.getRoles().add(Role.ROLE_USER);
//			userRepository.save(usuario1);
//			// Usuario 2 - Rol ADMIN
//			User usuario2 = new User();
//			usuario2.setFirstName("Bob");
//			usuario2.setLastName("Smith");
//			usuario2.setEmail("bob.smith@example.com");
//			usuario2.setPassword(passwordEncoder.encode("password456"));
//			usuario2.getRoles().add(Role.ROLE_ADMIN);
//			userRepository.save(usuario2);
//			// Usuario 3 - Rol USER
//			User usuario3 = new User();
//			usuario3.setFirstName("Carol");
//			usuario3.setLastName("Davis");
//			usuario3.setEmail("carol.davis@example.com");
//			usuario3.setPassword(passwordEncoder.encode("password789"));
//			usuario3.getRoles().add(Role.ROLE_USER);
//			userRepository.save(usuario3);
//
//		} catch (Exception e) {
//			String error = e.getMessage();
//
//			logger.info(error);
//		}

		Faker faker = new Faker(new Locale("es"));
		for (int i = 0; i < 10; i++) {
			Activity activity = new Activity();
			activity.setType(faker.lorem().word());
			activity.setDuration(Duration.ofMinutes(faker.number().randomDigit()));
			activity.setPlace(faker.address().fullAddress());
			activityRepository.save(activity);
		}

	}
}