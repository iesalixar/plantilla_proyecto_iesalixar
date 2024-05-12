package com.fitconnet.config.cors;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		// Allow requests from all origins
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));

		// Allow specified HTTP methods
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "UPDATE", "PATCH"));

		// Allow all headers
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

		// Create a UrlBasedCorsConfigurationSource and register the CORS configuration
		// for all endpoints
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}
}