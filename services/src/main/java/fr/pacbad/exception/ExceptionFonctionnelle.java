package fr.pacbad.exception;

import javax.ws.rs.core.Response.Status;

public class ExceptionFonctionnelle extends Exception {

	private static final long serialVersionUID = 1L;

	private Status status = Status.BAD_REQUEST;

	public ExceptionFonctionnelle() {
		super();
	}

	public ExceptionFonctionnelle(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ExceptionFonctionnelle(final String message) {
		super(message);
	}

	public ExceptionFonctionnelle(final Throwable cause) {
		super(cause);
	}

	public Status getStatus() {
		return status;
	}

	public ExceptionFonctionnelle setStatus(final Status status) {
		this.status = status;
		return this;
	}

}
