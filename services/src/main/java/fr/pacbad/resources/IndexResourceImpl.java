package fr.pacbad.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pacbad.Application;
import fr.pacbad.logger.PacbadLogger;

@Path("/")
public class IndexResourceImpl {

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(IndexResourceImpl.class);

	private static final String version = chargerVersion();

	private static String chargerVersion() {
		final InputStream is = IndexResourceImpl.class.getResourceAsStream("/build-info.properties");
		if (is != null) {
			final Properties props = new Properties();
			try {
				props.load(is);
				return props.getProperty("build.projectVersion");
			} catch (final IOException e) {
				LOGGER.warning("Impossible de charger la version : " + e.toString());
			}
		}
		return "???";
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
		return version;
	}

	@GET
	@Path("environnement")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEnvironnement() {
		return Application.getEnvironnement();
	}

}
