package br.soc.avaliacao.commons.exception;

public class UnauthorizedException extends ApiException {

    private static final long serialVersionUID = 401472172988916357L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message,Throwable cause) {
        super(message,cause);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
    
}
