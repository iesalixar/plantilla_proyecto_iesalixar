package com.fitconnet.config.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fitconnet.service.interfaces.entity.UserServiceI;
import com.fitconnet.service.interfaces.security.JwtServiceI;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

/**
 * Filter class for JWT-based authentication. This filter intercepts incoming
 * requests and validates JWT tokens for authentication.
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	/**
	 * Service for handling jwt operations.
	 */
	private final JwtServiceI jwtService;
	/**
	 * Service for handling user-related operations.
	 */
	private final UserServiceI userService;

	/**
	 * Performs JWT authentication for each incoming HTTP request.
	 *
	 * @param request     The HTTP servlet request.
	 * @param response    The HTTP servlet response.
	 * @param filterChain The filter chain for this request.
	 * @throws ServletException If the servlet encounters difficulty.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;

		// If Authorization header is missing or does not start with "Bearer ", continue
		// to the next filter
		if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Extract JWT token from Authorization header
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUserName(jwt);

		// If user email is not empty and there is no existing authentication context,
		// authenticate the user
		if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				// Create an authentication token and set it in the security context
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}

		// Continue with the filter chain
		filterChain.doFilter(request, response);
	}
}