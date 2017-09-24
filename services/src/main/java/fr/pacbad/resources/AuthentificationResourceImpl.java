package fr.pacbad.resources;

import java.io.IOException;

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
import fr.pacbad.exception.ExceptionFonctionnelle;
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
	public Claims login(final User user) throws ExceptionFonctionnelle {
		// Authenticate the user using the credentials provided
		userService.authenticate(user.getIdentifiant(), user.getPassword());

		// Lecture de l'utilisateur en base
		final User u = userService.getByIdentifiant(user.getIdentifiant());

		// Issue a token for the user
		final String token = userService.issueToken(u.getIdentifiant());

		final Jws<Claims> claims = userService.getClaims(token, u);

		// Return the token on the response
		return claims.getBody();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Claims register(final User user) throws ExceptionFonctionnelle, IOException {

		// Register the user
		userService.register(user);

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
		final User u = AuthFilter.getUser();
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
	public Claims updateUser(final User user) throws ExceptionFonctionnelle {
		if (user == null || user.getIdentifiant() == null) {
			throw new ExceptionFonctionnelle("Identifiant inconnu").setStatus(Status.BAD_REQUEST);
		}
		// Vérification que le compte modifié est bien celui de l'utilisateur connecté
		if (!user.getIdentifiant().equals(AuthFilter.getUserLogin())) {
			throw new ExceptionFonctionnelle("Impossible de modifier un autre compte : " + user.getIdentifiant())
					.setStatus(Status.BAD_REQUEST);
		}
		// Validation du mot de passe
		try {
			userService.authenticate(user.getIdentifiant(), user.getAncienPassword());
		} catch (final Exception e) {
			throw new ExceptionFonctionnelle("Mot de passe incorrect").setStatus(Status.BAD_REQUEST);
		}
		final User u = userService.updateCompte(user);
		final Jws<Claims> claims = userService.getClaims((String) AuthFilter.getClaims().getBody().get("token"), u);
		return claims.getBody();
	}

}
