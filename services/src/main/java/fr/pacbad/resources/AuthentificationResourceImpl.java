package fr.pacbad.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.pacbad.AuthNeeded;
import fr.pacbad.filter.AuthFilter;
import fr.pacbad.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Path("/auth")
public class AuthentificationResourceImpl {

	@Inject
	private UserService userService;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(final UserLogin user) {
		try {
			// Authenticate the user using the credentials provided
			userService.authenticate(user.login, user.password);

			// Issue a token for the user
			final String token = userService.issueToken(user.login);

			final Jws<Claims> claims = userService.getClaims(token);

			// Return the token on the response
			return Response.ok(claims.getBody()).build();

		} catch (final Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@AuthNeeded
	@Path("/logout")
	public Response logout() {
		// Ce service ne fait rien car le token restera valide encore un certain temps
		// Par contre, côté client il faudra supprimer le token du session storage du
		// navigateur
		return Response.noContent().build();
	}

	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public Object getUserInfo() {
		return AuthFilter.getClaims().getBody();
	}

	public static class UserLogin {
		public String login;
		public String password;
	}

}
