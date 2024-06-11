package com.fitconnet.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) setup. Enables
 * CORS for all origins, with specified allowed methods and headers.
 */
@Configuration
public class CorsConfig {

	/**
	 * Defines a CorsConfigurationSource bean to configure CORS for the application.
	 *
	 * @return CorsConfigurationSource object configured with allowed origins,
	 *         methods, and headers.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
			}
		};
	}
}