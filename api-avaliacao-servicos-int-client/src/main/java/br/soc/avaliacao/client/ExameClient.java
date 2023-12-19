package br.soc.avaliacao.client;



import org.springframework.cloud.openfeign.FeignClient;

import br.soc.avaliacao.rest.ExameRestService;

@FeignClient(name = "exame-client", url = "${URI_API_AVALIACAO}", path = "/exame")
public interface ExameClient extends ExameRestService {

}
