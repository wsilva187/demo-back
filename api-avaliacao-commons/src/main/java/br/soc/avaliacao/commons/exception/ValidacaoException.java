package br.soc.avaliacao.commons.exception;

public class ValidacaoException extends ApiRuntimeException {

	private static final long serialVersionUID = -1L;

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(String message) {
		this(message, null);
	}

	public ValidacaoException(Throwable cause) {
		this(null, cause);
	}

}
