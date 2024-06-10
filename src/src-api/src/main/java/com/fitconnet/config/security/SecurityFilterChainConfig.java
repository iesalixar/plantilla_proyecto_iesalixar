package com.fitconnet.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fitconnet.enums.Role;
import com.fitconnet.service.interfaces.entity.UserServiceI;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityFilterChainConfig {
	/**
	 * Filter for handling JWT authentication.
	 */
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	/**
	 * Service for handling user-related operations.
	 */
	private final UserServiceI userService;
	/**
	 * Encoder for encoding passwords.
	 */
	private final PasswordEncoder passwordEncoder;

	/**
	 * Configures the security filter chain.
	 *
	 * @param http The {@link HttpSecurity} object to configure security settings.
	 * @return A {@link SecurityFilterChain} object representing the security filter
	 *         chain configuration.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("/api/v1/activity/**")
						.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
						.requestMatchers("/api/v1/notification/**")
						.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
						.requestMatchers("/api/v1/user/**")
						.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
						.requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Configures the authentication provider.
	 *
	 * @return An {@link AuthenticationProvider} object for authenticating users.
	 */
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	/**
	 * Configures the authentication manager.
	 *
	 * @param config The {@link AuthenticationConfiguration} object.
	 * @return An {@link AuthenticationManager} object for managing authentication.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}