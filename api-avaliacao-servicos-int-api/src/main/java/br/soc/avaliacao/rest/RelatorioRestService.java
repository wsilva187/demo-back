package br.soc.avaliacao.rest;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.soc.avaliacao.dto.RelatorioExameResponseDTO;

public interface RelatorioRestService {

    @GetMapping
    byte[] gerarRelatorio (@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim);

    @GetMapping("/top")
    Page<RelatorioExameResponseDTO> findTop(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    
}
