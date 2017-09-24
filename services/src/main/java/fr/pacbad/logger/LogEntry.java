package fr.pacbad.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class LogEntry {

	Level level;
	String message;
	Throwable exception;
	long timestamp;
	Class<?> loggerClass;

	@Override
	public String toString() {
		final DateFormat sdf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE);
		String str = "";
		str += sdf.format(new Date(timestamp));
		str += " [" + level + "] [" + loggerClass.getSimpleName() + "] ";
		str += message;
		if (exception != null) {
			str += "\n";
			final StringWriter stacktrace = new StringWriter();
			exception.printStackTrace(new PrintWriter(stacktrace));
			str += stacktrace.toString();
		}
		return str;
	}
}
