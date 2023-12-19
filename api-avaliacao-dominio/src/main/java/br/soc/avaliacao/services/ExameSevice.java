package br.soc.avaliacao.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.services.vo.ExameVO;

public interface ExameSevice {

    boolean hasExameCadastrado(String nome);

    Exame salvarExame(Exame exame) throws ApiException;

    Exame buscarPorId(Long id) throws ApiException;

    void atualizarExame(Exame exame, ExameVO exameForm) throws ApiException;

    void deletarExame(Exame exame) throws ApiException;

    Page<Exame> buscarPaginado(Pageable pageable);

    Page<Exame> findTop(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

}
