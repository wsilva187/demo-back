package br.soc.avaliacao.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.RepositoryException;
import br.soc.avaliacao.commons.exception.ValidacaoException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.repository.ExameRealizadoRepository;
import br.soc.avaliacao.services.CustomMessageSource;
import br.soc.avaliacao.services.ExameRealizadoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExameRealizadoServiceImpl implements ExameRealizadoService {
    
    private final ExameRealizadoRepository repository;
    private final CustomMessageSource message;

    @Override
    public ExameRealizado addExame(Funcionario funcionario, Exame exame, LocalDateTime dataExame) throws ApiException {

        naoDeveriaSalvarExameJaRealizado(funcionario, exame, dataExame);
        
        try {
            ExameRealizado  exameRealizado = ExameRealizado.builder()
                .funcionario(funcionario)
                .exame(exame)
                .dataRealizacao(dataExame)
                .build();
            return repository.save(exameRealizado);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.exameRealizado.salvar"));
        }
        
    }
    private String toDateTimeBr(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter); }

    private void naoDeveriaSalvarExameJaRealizado(Funcionario funcionario, Exame exame, LocalDateTime dataExame) {
        Optional<ExameRealizado> obj = repository.findByFuncionarioAndExameAndDataRealizacao(funcionario, exame, dataExame);
        obj.ifPresent(e -> {
                throw new ValidacaoException(message.getMessage("erro.exameRealizado.existe", e.getNomeExame(), e.getNomeFuncionario(), toDateTimeBr(e.getDataCadastro())));
            });        
    }

    @Override
    public List<ExameRealizado> buscarPorData(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByDataRealizacaoBetween(dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX));
    }

}
