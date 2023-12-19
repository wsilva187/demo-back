package br.soc.avaliacao.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.dto.RelatorioExameResponseDTO;
import br.soc.avaliacao.facade.Facade;
import br.soc.avaliacao.mapper.ExameMapper;
import br.soc.avaliacao.rest.RelatorioRestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/relatorio")
public class RelatorioController implements RelatorioRestService {

    private final Facade facade;
    private final ExameMapper mapper;
    
    @Override
    @SneakyThrows
    public byte[] gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) {
        return facade.exportaCsv(dataInicio, dataFim);
    }

    @Override
    @SneakyThrows
    public Page<RelatorioExameResponseDTO> findTop(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Page<Exame> exames =  facade.findTop(dataInicio, dataFim, pageable);
        return exames.map(mapper::toRelDto);
    }   
}