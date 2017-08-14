package fr.pacbad;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class Application extends ResourceConfig {

	public static final String ENVIRONNEMENT_DEV = "dev";
	public static final String ENVIRONNEMENT_TEST = "test";
	public static final String ENVIRONNEMENT_PROD = "prod";

	public static String environnement = System.getProperty("pacbad.environnement");

	@Inject
	public Application(final ServiceLocator serviceLocator, final ServletContext context) {
		setApplicationName("PacBad");

		register(new ApplicationBinder());
		packages("fr.pacbad.resources;fr.pacbad.filter");
	}

	public static void initEnvironnement(final String env) {
		if (environnement == null) {
			environnement = env;
		}
	}

	public static String getEnvironnement() {
		if (environnement == null) {
			environnement = "dev";
		}
		return environnement;
	}

}