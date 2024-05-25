package com.fitconnet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fitconnet.enums.Role;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.UserRepository;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;

@Profile("demo")
@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

	static final boolean DELETE_USER = true;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final Faker faker;

	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		if (DELETE_USER) {
			userRepository.deleteAll();
		}

		try {
			User admin = new User();
			admin.setFirstName("admin");
			admin.setLastName("");
			admin.setUsername("bustaAdmin");
			admin.setEmail("admin@admin.com");
			admin.setAge(20);
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.getRoles().add(Role.ROLE_ADMIN);
			userRepository.save(admin);
			User both = new User();
			both.setFirstName("both");
			both.setLastName("");
			both.setUsername("bothAdUs");
			both.setEmail("both@admin.com");
			both.setAge(20);
			both.setPassword(passwordEncoder.encode("admin"));
			both.getRoles().add(Role.ROLE_ADMIN);
			both.getRoles().add(Role.ROLE_USER);
			userRepository.saveAndFlush(both);

			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setFirstName(faker.name().firstName());
				user.setLastName(faker.name().lastName());
				user.setUsername(faker.name().username());
				user.setEmail(faker.internet().emailAddress());
				user.setAge(faker.number().numberBetween(18, 80));
				// String age = String.valueOf(faker.number().numberBetween(18, 80));
				user.setPassword(passwordEncoder.encode(faker.internet().password()));
				user.getRoles().add(Role.ROLE_USER);
				userRepository.save(user);
			}

		} catch (Exception e) {
			String error = e.getMessage();
			logger.info(error);
		}

	}
}