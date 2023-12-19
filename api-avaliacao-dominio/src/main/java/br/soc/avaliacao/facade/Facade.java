package br.soc.avaliacao.facade;


import static br.soc.avaliacao.commons.util.AssertsUtil.assertTrue;
import static br.soc.avaliacao.commons.util.AssertsUtil.startDateAfterEndDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.services.CsvService;
import br.soc.avaliacao.services.CustomMessageSource;
import br.soc.avaliacao.services.ExameRealizadoService;
import br.soc.avaliacao.services.ExameSevice;
import br.soc.avaliacao.services.FuncionarioService;
import br.soc.avaliacao.services.vo.ExameVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Facade {

    private final ExameSevice exameService;
    private final CsvService exportService;
    private final CustomMessageSource message;
    private final FuncionarioService funcionarioService;
    private final ExameRealizadoService exameRealizadoService;

    public Exame salvarExame(String nome) throws ApiException {
        log.debug("Salvando exame {}.", nome);

        assertTrue(exameService.hasExameCadastrado(nome), message.getMessage("erro.exame.nomeRepetido", nome));
        
        return exameService.salvarExame(Exame.builder().nome(nome.trim()).build());
    }

    public Exame buscarExamePorId(Long id) throws ApiException {
        log.debug("Buscando exame com id {}.", id);

        return exameService.buscarPorId(id);
    }

    public void atualizarExame(Long id, ExameVO exameForm) throws ApiException {
        log.debug("Atualizando exame com id {}.", id);

        Exame  exame = exameService.buscarPorId(id);
        exameService.atualizarExame(exame, exameForm);
    }

    public void deletarExame(Long id) throws ApiException {
        log.debug("Deletando exame com id {}.", id);

        Exame  exame = exameService.buscarPorId(id);
        exameService.deletarExame(exame);
    }

    public Page<Exame> buscarExamePaginado(Pageable pageable) {
        return exameService.buscarPaginado(pageable);
    }

    public Funcionario salvarFuncionario(String nome) throws ApiException {
        log.debug("Salvando funcionario {}.", nome);

        return funcionarioService.salvarFuncionario(Funcionario.builder().nome(nome.trim()).build());
    }

    public Funcionario buscarFuncionarioPorId(UUID id) throws ApiException {
        log.debug("Buscando funcionario com id {}.", id);
        
        return funcionarioService.buscarPorUuid(id);
    }

    public void deletarFuncionario(UUID id)throws ApiException {
        log.debug("Buscando funcionario com id {}.", id);
        
        Funcionario  funcionario = funcionarioService.buscarPorUuid(id);
        funcionarioService.deletarFuncionario(funcionario);
    }

    public ExameRealizado addExame(UUID idFuncionario, Long idExame, LocalDateTime dataExame) throws ApiException {
        log.debug("Adicionando exame para funcionario com id {}.", idFuncionario);
        Funcionario  funcionario = funcionarioService.buscarPorUuid(idFuncionario);
        Exame exame = exameService.buscarPorId(idExame);
        return exameRealizadoService.addExame(funcionario, exame, dataExame);
    }

    public byte[] exportaCsv(LocalDate dataInicio, LocalDate dataFim) throws ApiException {
        log.debug("Gerando CSV: {} - {}", dataInicio, dataFim);

        startDateAfterEndDate(dataInicio, dataFim, message.getMessage("erro.data.intervaloInvalido"));

        List<ExameRealizado> examesRealizados = exameRealizadoService.buscarPorData(dataInicio, dataFim);

        return exportService.exportaCsv(examesRealizados);
    }

    public Page<Exame> findTop(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        return exameService.findTop(dataInicio, dataFim, pageable);
    }

    public Page<Funcionario> buscarFuncionarioPaginado(Pageable pageable) {
        return funcionarioService.buscarPaginado(pageable);
    }

    public void atualizarFuncionario(UUID id, String nome) throws ApiException {
        Funcionario funcionario = funcionarioService.buscarPorUuid(id);
        funcionarioService.atualizarFuncionario(funcionario, nome);
    }

}
