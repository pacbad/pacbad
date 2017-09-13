package fr.pacbad;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import fr.pacbad.filter.CorsFilter;
import fr.pacbad.logger.PacbadLogger;

public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(CorsFilter.class);

	@Override
	public Response toResponse(final WebApplicationException ex) {
		if (ex.getResponse().getStatus() >= 500) {
			LOGGER.error(ex.toString(), ex);
		}
		return Response.status(ex.getResponse().getStatus()).entity(new ExceptionObject(ex))
				.type(MediaType.APPLICATION_JSON).build();
	}

	private static class ExceptionObject {

		private final String message;

		@SuppressWarnings("unused")
		public ExceptionObject() {
			this.message = "Exception inconnue";
		}

		public ExceptionObject(final WebApplicationException t) {
			this.message = t.getMessage();
		}

		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}
	}

}