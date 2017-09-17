package fr.pacbad.resources;

import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class IndexResourceImpl {

	// private static final PacbadLogger LOGGER =
	// PacbadLogger.getLogger(IndexResourceImpl.class);

	private static final Properties buildProps = chargerBuildProps();

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

}
