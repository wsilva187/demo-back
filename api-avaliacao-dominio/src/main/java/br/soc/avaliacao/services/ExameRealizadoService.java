package br.soc.avaliacao.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;

public interface ExameRealizadoService {

    ExameRealizado addExame(Funcionario funcionario, Exame exame, LocalDateTime dataExame) throws ApiException;

    List<ExameRealizado> buscarPorData(LocalDate dataInicio, LocalDate dataFim);
    
}
