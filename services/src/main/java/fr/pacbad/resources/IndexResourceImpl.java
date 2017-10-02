package fr.pacbad.resources;

import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pacbad.entities.InfosHeader;
import fr.pacbad.services.TournoiService;

@Path("/")
public class IndexResourceImpl {

	// private static final PacbadLogger LOGGER =
	// PacbadLogger.getLogger(IndexResourceImpl.class);

	private static final Properties buildProps = chargerBuildProps();

	@Inject
	private TournoiService tournoiService;

	private static Properties chargerBuildProps() {
		final InputStream is = IndexResourceImpl.class.getResourceAsStream("/build-info.properties");
		if (is != null) {
			final Properties props = new Properties();
			return props;
		}
		return new Properties();
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getIndex() {
		return "It works!";
	}

	@GET
	@Path("version")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVersion() {
		return buildProps.getProperty("build.projectVersion", "?");
	}

	@GET
	@Path("buildDate")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBuildDate() {
		return buildProps.getProperty("build.timestamp", "?");
	}

	@GET
	@Path("infosHeader")
	@Produces(MediaType.APPLICATION_JSON)
	public InfosHeader getInfosHeader() {
		final InfosHeader infos = new InfosHeader();
		infos.setNbTournois(tournoiService.count());
		return infos;
	}

}
