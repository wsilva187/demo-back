package br.soc.avaliacao.commons.exception;

public abstract class ApiException extends Exception {

	private static final long serialVersionUID = -879098790987909879L;

	protected ApiException() {
		super();
	}

	protected ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ApiException(String message) {
		super(message);
	}

	protected ApiException(Throwable cause) {
		super(cause);
	}

}
