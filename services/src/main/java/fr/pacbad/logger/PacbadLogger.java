package fr.pacbad.logger;

import java.util.ArrayList;
import java.util.List;

import fr.pacbad.Application;

public class PacbadLogger {

	private final Class<?> clazz;

	private final List<LogHandler> handlers = new ArrayList<>();

	private PacbadLogger(final Class<?> clazz) {
		this.clazz = clazz;
	}

	public static PacbadLogger getLogger(final Class<?> clazz) {

		final PacbadLogger logger = new PacbadLogger(clazz);

		// Configuration
		switch (Application.getEnvironnement()) {
		case Application.ENVIRONNEMENT_DEV:
		default:
			logger.handlers.add(new ConsoleWriter());
		}

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

	public void log(final Level level, final String message) {
		final LogEntry entry = new LogEntry();
		entry.timestamp = System.currentTimeMillis();
		entry.level = level;
		entry.message = message;
		entry.loggerClass = clazz;
		for (final LogHandler handler : handlers) {
			handler.log(entry);
		}
	}

}
