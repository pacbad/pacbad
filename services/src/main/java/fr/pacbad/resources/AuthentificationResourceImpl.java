package fr.pacbad.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.pacbad.AuthNeeded;
import fr.pacbad.entities.User;
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
	public Response login(final User user) {
		try {
			// Authenticate the user using the credentials provided
			userService.authenticate(user.getIdentifiant(), user.getPassword());

			// Issue a token for the user
			final String token = userService.issueToken(user.getIdentifiant());

			final Jws<Claims> claims = userService.getClaims(token);

			// Return the token on the response
			return Response.ok(claims.getBody()).build();

		} catch (final Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(final User user) {

		try {
			// Register the user
			userService.register(user);
		} catch (final Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}

		return login(user);
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

	@POST
	@Path("/validate")
	@AuthNeeded
	public void validate() {
		// Rien à faire
	}

	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public Object getUserInfo() {
		return AuthFilter.getClaims().getBody();
	}

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public User getUser() {
		final User u = userService.getByIdentifiant(AuthFilter.getUserLogin());
		u.setHash(null);
		u.setAncienPassword(null);
		u.setPassword(null);
		u.setDateNaissance(null);
		return u;
	}

	@PUT
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public Response updateUser(final User user) {
		if (user == null || user.getIdentifiant() == null) {
			return Response.status(Status.NOT_ACCEPTABLE).entity("Identifiant inconnu").build();
		}
		// Vérification que le compte modifié est bien celui de l'utilisateur connecté
		if (!user.getIdentifiant().equals(AuthFilter.getUserLogin())) {
			return Response.status(Status.NOT_ACCEPTABLE)
					.entity("Impossible de modifier un autre compte : " + user.getIdentifiant()).build();
		}
		// Validation du mot de passe
		try {
			userService.authenticate(user.getIdentifiant(), user.getAncienPassword());
		} catch (final Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE)
					.entity("Mot de passe incorrect : " + user.getIdentifiant() + " / " + user.getAncienPassword())
					.build();
		}
		final User u = userService.updateCompte(user);
		final Claims claims = AuthFilter.getClaims().getBody();
		claims.put("mail", u.getMail());
		return Response.ok(claims).build();
	}

}
