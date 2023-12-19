package br.soc.avaliacao.commons.exception;

public class ForbiddenException extends ApiRuntimeException {

    private static final long serialVersionUID = 401472172988916357L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message,Throwable cause) {
        super(message,cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
    
}
