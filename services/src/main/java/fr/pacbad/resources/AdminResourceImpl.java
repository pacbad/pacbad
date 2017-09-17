package fr.pacbad.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.pacbad.AuthNeeded;
import fr.pacbad.entities.Parametre;
import fr.pacbad.entities.User;
import fr.pacbad.entities.ref.RoleUtilisateurEnum;
import fr.pacbad.exception.ExceptionFonctionnelle;
import fr.pacbad.filter.AuthFilter;
import fr.pacbad.filter.TransactionFilter;
import fr.pacbad.services.ParametreService;
import fr.pacbad.services.UserService;

@Path("/admin")
@AuthNeeded(role = RoleUtilisateurEnum.ADMIN)
public class AdminResourceImpl {

	// private static final PacbadLogger LOGGER =
	// PacbadLogger.getLogger(AdminResourceImpl.class);

	@Inject
	private ParametreService parametres;

	@Inject
	private UserService users;

	@DELETE
	@Path("cache")
	public void clearCache() {
		TransactionFilter.getEntityManager().getEntityManagerFactory().getCache().evictAll();
	}

	@GET
	@Path("administrateur")
	public List<User> getAdministrateurs() {
		return users.getAdministrateurs();
	}

	@DELETE
	@Path("administrateur/{identifiant}")
	public void deleteAdministrateur(@PathParam("identifiant") final String identifiant) throws ExceptionFonctionnelle {
		// Un adminsitrateur ne peut pas se supprimer lui même
		if (identifiant.equals(AuthFilter.getUserLogin())) {
			throw new ExceptionFonctionnelle("Impossible de se supprimer soi-même en tant qu'administrateur");
		}
		users.deleteAdministrateur(identifiant);
	}

	@PUT
	@Path("administrateur/{identifiant}")
	public void addAdministrateur(@PathParam("identifiant") final String identifiant) throws ExceptionFonctionnelle {
		// Un adminsitrateur ne peut pas supprimer lui même
		users.addAdministrateur(identifiant);
	}

	@GET
	@Path("parametre")
	public List<Parametre> getParametres() {
		return parametres.getAll();
	}

	@PUT
	@Path("parametre")
	public void updateParametre(final Parametre parametre) {
		parametres.createOrUpdate(parametre);
	}

}
