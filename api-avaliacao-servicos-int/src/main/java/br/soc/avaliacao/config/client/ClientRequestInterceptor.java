package br.soc.avaliacao.config.client;


import org.springframework.stereotype.Component;

import br.soc.avaliacao.services.TokenContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientRequestInterceptor implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    private final TokenContext tokenContext;

    @Override
    public void apply(RequestTemplate template) {
        String token = tokenContext.getToken();
        String headerValue = String.format("%s %s", BEARER, token);
        template.header(AUTHORIZATION, headerValue);
    }

}
