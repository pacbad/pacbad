package fr.pacbad.filter;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import fr.pacbad.AuthNeeded;
import fr.pacbad.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Priority(Priorities.AUTHENTICATION)
@Provider
@AuthNeeded
public class AuthFilter implements ContainerRequestFilter {

	private static final ThreadLocal<Jws<Claims>> THREAD_LOCAL_CLAIMS = new ThreadLocal<>();

	@Inject
	private UserService userService;

	@Override
	public void filter(final ContainerRequestContext request) {
		System.out.println("Start AuthFilter");

		// Get the HTTP Authorization header from the request
		final String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null) {
			throw new WebApplicationException("Missing Authorization header", Response.Status.UNAUTHORIZED);
		}

		// Extract the token from the HTTP Authorization header
		final String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			final Jws<Claims> claims = userService.getClaims(token);
			THREAD_LOCAL_CLAIMS.set(claims);

		} catch (final Exception e) {
			throw new WebApplicationException(e, Response.Status.UNAUTHORIZED);
		}
	}

	public static Jws<Claims> getClaims() {
		return THREAD_LOCAL_CLAIMS.get();
	}

	public static String getUserLogin() {
		if (getClaims() != null) {
			return getClaims().getBody().getSubject();
		}
		return null;
	}

}
