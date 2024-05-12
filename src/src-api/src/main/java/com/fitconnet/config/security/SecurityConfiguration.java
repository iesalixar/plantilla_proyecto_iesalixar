package com.fitconnet.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fitconnet.enums.Role;
import com.fitconnet.service.interfaces.entity.UserServiceI;

import lombok.AllArgsConstructor;

/**
 * Configuration class for security setup in the application. Configures
 * authentication, authorization, JWT authentication filter, and password
 * encoding.
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
	/**
	 * Dependency injection for the JwtAuthenticationFilter class.
	 */
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	/**
	 * Dependency injection for the UserServiceI interface.
	 */
	private final UserServiceI userService;

	/**
	 * Configures the security filter chain.
	 *
	 * @param http The HttpSecurity object to configure security settings.
	 * @return SecurityFilterChain object representing the security filter chain
	 *         configuration.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> request
				// Permit access to specific endpoints based on roles
				.requestMatchers("/api/v1/auth/**").permitAll().requestMatchers("/api/v1/actividades/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers("/api/v1/notification/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers("/api/v1/user/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				// Permit access to Swagger documentation endpoints
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				// Require authentication for any other request
				.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Configures the password encoder.
	 *
	 * @return PasswordEncoder object for encoding passwords.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures the authentication provider.
	 *
	 * @return AuthenticationProvider object for authenticating users.
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Configures the authentication manager.
	 *
	 * @param config The AuthenticationConfiguration object.
	 * @return AuthenticationManager object for managing authentication.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}