package fr.pacbad.logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.pacbad.services.ParametreService;

public class PacbadLogger {

	@Inject
	private ParametreService parametre;

	private final Class<?> clazz;

	private final List<LogHandler> handlers = new ArrayList<>();

	private PacbadLogger(final Class<?> clazz) {
		this.clazz = clazz;
	}

	public static PacbadLogger getLogger(final Class<?> clazz) {

		final PacbadLogger logger = new PacbadLogger(clazz);

		// Configuration
		logger.handlers.add(new ConsoleWriter());

		return logger;
	}

	public void debug(final String message) {
		log(Level.DEBUG, message);
	}

	public void info(final String message) {
		log(Level.INFO, message);
	}

	public void warn(final String message) {
		log(Level.WARN, message);
	}

	public void warning(final String message) {
		warn(message);
	}

	public void error(final String message) {
		log(Level.ERROR, message);
	}

	public void error(final String message, final Throwable t) {
		log(Level.ERROR, message, t);
	}

	private void log(final Level level, final String message) {
		log(level, message, null);
	}

	private void log(final Level level, final String message, final Throwable t) {
		final LogEntry entry = new LogEntry();
		entry.timestamp = System.currentTimeMillis();
		entry.level = level;
		entry.message = message;
		entry.exception = t;
		entry.loggerClass = clazz;
		final Level levelMin;
		if (parametre == null) {
			// Dans le cas des tests, le service des paramètres n'est pas injecté
			levelMin = Level.DEBUG;
		} else {
			levelMin = Level.valueOf(parametre.getString(ParametreService.KEY_LOGGER_LEVEL));

		}
		if (levelMin.ordinal() <= level.ordinal()) {
			for (final LogHandler handler : handlers) {
				handler.log(entry);
			}
		}
	}

}
