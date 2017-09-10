package fr.pacbad.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pacbad.entities.Tournoi;
import fr.pacbad.services.TournoiService;

@Path("/tournoi")
public class TournoiResourceImpl {
	@Inject
	private TournoiService tournoiService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tournoi> getTournois() {
		return tournoiService.getAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tournoi getTournoi(@PathParam("id") final Long id) {
		return tournoiService.getById(id);
	}
}
