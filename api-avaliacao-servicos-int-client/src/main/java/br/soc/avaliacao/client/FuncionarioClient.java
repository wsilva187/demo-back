package br.soc.avaliacao.client;


import org.springframework.cloud.openfeign.FeignClient;

import br.soc.avaliacao.rest.FuncionarioRestService;

@FeignClient(name = "funcionario-client", url = "${URI_API_AVALIACAO}", path = "/funcionario")
public interface FuncionarioClient extends FuncionarioRestService {

}
