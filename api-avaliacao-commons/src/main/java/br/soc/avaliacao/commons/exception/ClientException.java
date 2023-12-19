package br.soc.avaliacao.commons.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final Object body;

    public ClientException(Object body, int status) {
        this.status = status;
        this.body = body;
    }

}
