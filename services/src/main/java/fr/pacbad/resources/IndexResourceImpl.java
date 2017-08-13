package fr.pacbad.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class IndexResourceImpl {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getIndex() {
		return "It works!";
	}
	
}
