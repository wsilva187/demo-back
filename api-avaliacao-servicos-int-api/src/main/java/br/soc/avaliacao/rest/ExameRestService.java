package br.soc.avaliacao.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.soc.avaliacao.dto.ExameRequestDTO;
import br.soc.avaliacao.dto.ExameResponseDTO;

public interface ExameRestService {
    
    @PostMapping
    ExameResponseDTO createExame(@RequestBody ExameRequestDTO exameDto);

    @GetMapping("/{id}")
    ExameResponseDTO getExameById(@PathVariable Long id);

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateExame(@PathVariable Long id, @RequestBody ExameRequestDTO exameDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteExame(@PathVariable Long id);

    @GetMapping
    Page<ExameResponseDTO> buscarPaginado(Pageable pageable);
}
