package com.fitconnet.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.productos.entities.Producto;
import com.api.productos.entities.Role;
import com.api.productos.entities.Usuario;
import com.api.productos.repositories.interfaces.ProductoRepository;
import com.api.productos.repositories.interfaces.UserRepository;
import com.github.javafaker.Faker;

@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

	static final boolean BORRAR_PRODUCTO = true;

	private final UserRepository usuarioRepository;

	private final ProductoRepository productoRepository;

	private final PasswordEncoder passwordEncoder;

	public InitializationData(UserRepository usuarioRepository, ProductoRepository productoRepository,
			PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.productoRepository = productoRepository;
		this.passwordEncoder = passwordEncoder;
	}

	private static final Logger logger = LoggerFactory.getLogger(InitializationData.class);

	@Override
	public void run(String... args) throws Exception {

		if (BORRAR_PRODUCTO) {
			productoRepository.deleteAll();
		}

		try {
			// Usuario 1 - Rol USER
			User usuario1 = new User();
			usuario1.setFirstName("Alice");
			usuario1.setLastName("Johnson");
			usuario1.setEmail("alice.johnson@example.com");
			usuario1.setPassword(passwordEncoder.encode("password123"));
			usuario1.getRoles().add(Role.ROLE_USER);
			usuarioRepository.save(usuario1);
			// Usuario 2 - Rol ADMIN
			User usuario2 = new User();
			usuario2.setFirstName("Bob");
			usuario2.setLastName("Smith");
			usuario2.setEmail("bob.smith@example.com");
			usuario2.setPassword(passwordEncoder.encode("password456"));
			usuario2.getRoles().add(Role.ROLE_ADMIN);
			usuarioRepository.save(usuario2);
			// Usuario 3 - Rol USER
			User usuario3 = new User();
			usuario3.setFirstName("Carol");
			usuario3.setLastName("Davis");
			usuario3.setEmail("carol.davis@example.com");
			usuario3.setPassword(passwordEncoder.encode("password789"));
			usuario3.getRoles().add(Role.ROLE_USER);
			usuarioRepository.save(usuario3);

		} catch (Exception e) {
			String error = e.getMessage();

			logger.info(error);
		}

		Faker faker = new Faker(new Locale("es"));
		for (int i = 0; i < 10; i++) {
			Producto producto = new Producto();
			producto.setCodigo(faker.number().digits(10));
			producto.setNombre(faker.commerce().productName());
			producto.setPrecio(faker.number().randomDouble(2, 1, 1000));
			productoRepository.save(producto);
		}

	}
}