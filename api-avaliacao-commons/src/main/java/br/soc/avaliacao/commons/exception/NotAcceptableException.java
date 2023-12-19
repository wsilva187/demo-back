package br.soc.avaliacao.commons.exception;

public class NotAcceptableException extends ApiException {

    private static final long serialVersionUID = 401472172988916357L;

    public NotAcceptableException() {
        super();
    }

    public NotAcceptableException(String message,Throwable cause) {
        super(message,cause);
    }

    public NotAcceptableException(String message) {
        super(message);
    }

    public NotAcceptableException(Throwable cause) {
        super(cause);
    }
    
}