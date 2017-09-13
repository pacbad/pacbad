package fr.pacbad.resources;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import fr.pacbad.AuthNeeded;
import fr.pacbad.entities.User;
import fr.pacbad.exception.ExceptionFonctionnelle;
import fr.pacbad.filter.AuthFilter;
import fr.pacbad.services.UserService;

@Path("/demo")
public class DemoAuthResourceImpl {

	@Inject
	private UserService userService;

	@GET
	@Path("/getNoAuth")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessageNoAuth() {
		return "getMessageNoAuth";
	}

	@GET
	@Path("/getWithAuth")
	@Produces(MediaType.TEXT_PLAIN)
	@AuthNeeded
	public String getMessageWithAuth() {
		return "getMessageWithAuth";
	}

	@POST
	@Path("/postWithAuth")
	@Produces(MediaType.APPLICATION_JSON)
	@AuthNeeded
	public User setPassword(final String password) {
		final User u = userService.getByIdentifiant(AuthFilter.getUserLogin());
		u.setHash(userService.hash(password));
		userService.update(u);
		return u;
	}

	@GET
	@Path("exceptionFonctionnelle")
	@Produces(MediaType.APPLICATION_JSON)
	public void testExceptionFonctionnelle() throws ExceptionFonctionnelle {
		final Exception cause = new Exception("cause");
		throw new ExceptionFonctionnelle("Mon exception fonctionnelle", cause).setStatus(Status.FORBIDDEN);
	}

	@GET
	@Path("exceptionTechnique")
	@Produces(MediaType.APPLICATION_JSON)
	public void testExceptionTechnique() {
		final IOException cause = new IOException("cause");
		throw new RuntimeException("Mon exception technique", cause);
	}

}
