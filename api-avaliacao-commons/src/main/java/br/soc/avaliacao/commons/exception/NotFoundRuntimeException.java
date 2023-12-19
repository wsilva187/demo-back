package br.soc.avaliacao.commons.exception;

public class NotFoundRuntimeException extends ApiRuntimeException {

	public NotFoundRuntimeException() {
		super();
	}

	public NotFoundRuntimeException(String message, Throwable cause) {
		super(message,cause);
	}

	public NotFoundRuntimeException(String message) {
		super(message);
	}

	public NotFoundRuntimeException(Throwable cause) {
		super(cause);
	}

}
