package br.soc.avaliacao.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.soc.avaliacao.domain.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

    boolean existsByNomeIgnoreCase(String nome);

    //select e.nome from exames_realizados er join exame e on e.id=er.exame_id group by e.nome order by count(e.nome) desc
    @Query("SELECT new Exame(e.id, e.nome, count(e.id) as total) " +
            "FROM ExameRealizado er " +
            "JOIN er.exame e " +
            "WHERE er.dataRealizacao BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY e.id, e.nome " +
            "ORDER BY COUNT(er.id) DESC")
    Page<Exame> findTop(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
}
