package br.soc.avaliacao.services;

import java.util.List;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.domain.ExameRealizado;

public interface CsvService {

	byte[] exportaCsv(List<ExameRealizado> exames) throws ApiException;

}
