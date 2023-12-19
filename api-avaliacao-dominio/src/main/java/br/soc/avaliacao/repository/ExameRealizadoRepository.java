package br.soc.avaliacao.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;

public interface ExameRealizadoRepository extends JpaRepository<ExameRealizado, Long> {

    Optional<ExameRealizado> findByFuncionarioAndExameAndDataRealizacao(Funcionario funcionario, Exame exame, LocalDateTime dataExame);

    List<ExameRealizado> findByDataRealizacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
}
