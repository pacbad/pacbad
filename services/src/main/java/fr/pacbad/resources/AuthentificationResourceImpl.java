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
	public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {
		try {
			// Authenticate the user using the credentials provided
			userService.authenticate(login, password);

			// Issue a token for the user
			final String token = userService.issueToken(login);

			// Return the token on the response
			return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public Object getUserInfo() {
		return AuthFilter.getClaims().getBody();
	}

}
