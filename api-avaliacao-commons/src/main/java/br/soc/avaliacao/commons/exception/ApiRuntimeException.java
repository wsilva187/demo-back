package br.soc.avaliacao.commons.exception;

public class ApiRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -879098790987909879L;

    public ApiRuntimeException() {
        super();
    }

    public ApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRuntimeException(String message) {
        super(message);
    }

    public ApiRuntimeException(Throwable cause) {
        super(cause);
    }

}
