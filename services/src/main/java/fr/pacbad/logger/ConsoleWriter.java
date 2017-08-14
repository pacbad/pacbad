package fr.pacbad.logger;

public class ConsoleWriter implements LogHandler {

	@Override
	public void log(final LogEntry log) {
		System.out.println(log);
	}

}
