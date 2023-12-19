package br.soc.avaliacao.commons.exception;

public class BadRequestException extends ApiException {

    private static final long serialVersionUID = 401472172988916357L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message,Throwable cause) {
        super(message,cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
    
}