package br.soc.avaliacao.services;

import org.springframework.stereotype.Component;

import br.soc.avaliacao.commons.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenContext {

	private String token;
    private String contexto;
    private final CustomMessageSource message;

    public String getToken() {
        try {
            return token.replace("Bearer ", "");
        } catch (Exception e) {
            throw new ForbiddenException(message.getMessage("erro.token.nao_encontrado"), e);
        }
    }

    public String getTokenBearer() {
        try {
            return token;
        } catch (Exception e) {
            throw new ForbiddenException(message.getMessage("erro.token.nao_encontrado"), e);
        }
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

}
