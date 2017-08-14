package fr.pacbad.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.pacbad.AuthNeeded;
import fr.pacbad.filter.AuthFilter;
import fr.pacbad.services.UserService;

@Path("/auth")
public class AuthentificationResourceImpl {

	@Inject
	private UserService userService;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("login") final String login, @FormParam("password") final String password) {
		try {
			// Authenticate the user using the credentials provided
			userService.authenticate(login, password);

			// Issue a token for the user
			final String token = userService.issueToken(login);

			// Return the token on the response
			return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

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

}
