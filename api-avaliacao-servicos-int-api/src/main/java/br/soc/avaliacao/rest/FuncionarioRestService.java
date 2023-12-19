package br.soc.avaliacao.rest;

import java.util.UUID;

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

import br.soc.avaliacao.dto.ExameRealizadoRequestDTO;
import br.soc.avaliacao.dto.ExameRealizadoResponseDTO;
import br.soc.avaliacao.dto.ExameRequestDTO;
import br.soc.avaliacao.dto.FuncionarioResponseDTO;


public interface FuncionarioRestService {

    @PostMapping
    FuncionarioResponseDTO createFuncionario(@RequestBody ExameRequestDTO dto);

    @GetMapping("/{id}")
    FuncionarioResponseDTO getFuncionarioById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateFuncionario(@PathVariable UUID id, @RequestBody ExameRequestDTO dto);

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteFuncionario(@PathVariable UUID id);

    @GetMapping
    Page<FuncionarioResponseDTO> buscarPaginado(Pageable pageable);

    @PostMapping("/add-exame")
    ExameRealizadoResponseDTO addExame(@RequestBody ExameRealizadoRequestDTO dto);
    
}