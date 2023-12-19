package br.soc.avaliacao.commons.exception;

public class NotFoundException extends ApiException {

	private static final long serialVersionUID = 7573776262756782934L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message,cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
