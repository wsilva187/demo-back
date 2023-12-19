package br.soc.avaliacao.client;


import org.springframework.cloud.openfeign.FeignClient;

import br.soc.avaliacao.rest.RelatorioRestService;

@FeignClient(name = "funcionario-client", url = "${URI_API_AVALIACAO}", path = "/relatorio")
public interface RelatorioClient extends RelatorioRestService {
    
}
