package fr.pacbad.resources;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pacbad.entities.Instance;
import fr.pacbad.services.PoonaService;

@Path("/poona")
public class PoonaResourceImpl {
	@Inject
	private PoonaService poonaService;

	@GET
	@Path("/federation")
	@Produces(MediaType.APPLICATION_JSON)
	public Instance getFederation() throws IOException {
		final List<Instance> instances = poonaService.getFederation().getInstances();
		if (instances.isEmpty()) {
			return null;
		}
		return instances.get(0);
	}

	@GET
	@Path("/federation/ligue")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Instance> getLigues() throws IOException {
		return poonaService.getInstanceFromParent(1L).getInstances();
	}

	@GET
	@Path("/federation/ligue/{idLigue}/codep")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Instance> getCodeps(@PathParam("idLigue") final Long idLigue) throws IOException {
		return poonaService.getInstanceFromParent(idLigue).getInstances();
	}

	@GET
	@Path("/federation/ligue/{idLigue}/codep/{idCodep}/club")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Instance> getClubs(@PathParam("idLigue") final Long idLigue, @PathParam("idCodep") final Long idCodep)
			throws IOException {
		return poonaService.getInstanceFromParent(idCodep).getInstances();
	}

	@GET
	@Path("/federation/club/{idClub}")
	@Produces(MediaType.APPLICATION_JSON)
	public Instance getClub(@PathParam("idClub") final Long idClub) throws IOException {
		return poonaService.getInstanceById(idClub).getInstance();
	}
}
