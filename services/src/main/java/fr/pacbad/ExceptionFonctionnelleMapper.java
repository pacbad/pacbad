package fr.pacbad;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import fr.pacbad.exception.ExceptionFonctionnelle;

public class ExceptionFonctionnelleMapper implements ExceptionMapper<ExceptionFonctionnelle> {

	@Override
	public Response toResponse(final ExceptionFonctionnelle ex) {
		return Response.status(ex.getStatus()).entity(new ExceptionObject(ex)).type(MediaType.APPLICATION_JSON).build();
	}

	private static class ExceptionObject {

		private final String message;

		private final int status;

		@SuppressWarnings("unused")
		public ExceptionObject() {
			this.message = "Exception inconnue";
			this.status = Status.BAD_REQUEST.getStatusCode();
		}

		public ExceptionObject(final ExceptionFonctionnelle t) {
			this.message = t.getMessage();
			this.status = t.getStatus().getStatusCode();
		}

		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}

		@SuppressWarnings("unused")
		public int getStatus() {
			return status;
		}
	}

}
