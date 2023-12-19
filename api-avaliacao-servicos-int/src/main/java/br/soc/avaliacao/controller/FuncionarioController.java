package br.soc.avaliacao.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.dto.ExameRealizadoRequestDTO;
import br.soc.avaliacao.dto.ExameRealizadoResponseDTO;
import br.soc.avaliacao.dto.ExameRequestDTO;
import br.soc.avaliacao.dto.FuncionarioResponseDTO;
import br.soc.avaliacao.facade.Facade;
import br.soc.avaliacao.mapper.ExameRealizadoMapper;
import br.soc.avaliacao.mapper.FuncionarioMapper;
import br.soc.avaliacao.rest.FuncionarioRestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/funcionario")
public class FuncionarioController implements FuncionarioRestService {

    private final Facade facade;
    private final FuncionarioMapper funcionarioMapper;
    private final ExameRealizadoMapper exameRealizadoMapper;

    @Override
    @SneakyThrows
    public FuncionarioResponseDTO createFuncionario(ExameRequestDTO dto) {
        Funcionario funcionario = facade.salvarFuncionario(dto.getNome());
        return funcionarioMapper.toDto(funcionario);
    }

    @Override
    @SneakyThrows
    public FuncionarioResponseDTO getFuncionarioById(UUID id) {
        Funcionario funcionario = facade.buscarFuncionarioPorId(id);
        return funcionarioMapper.toDto(funcionario);
    }

    @Override
    @SneakyThrows
    public void updateFuncionario(UUID id, ExameRequestDTO exameDto) {
        facade.atualizarFuncionario(id, exameDto.getNome());
    }

    @Override
    @SneakyThrows
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFuncionario(UUID id) {
        facade.deletarFuncionario(id);
    }

    @Override
    @SneakyThrows
    public Page<FuncionarioResponseDTO> buscarPaginado(Pageable pageable) {
        Page<Funcionario> funcionarios = facade.buscarFuncionarioPaginado(pageable);
        return funcionarios.map(funcionarioMapper::toDto);
    }

    @Override
    @SneakyThrows
    public ExameRealizadoResponseDTO addExame(ExameRealizadoRequestDTO dto) {
        ExameRealizado exameRealizado = facade.addExame(dto.getIdFuncionario(), dto.getIdExame(), dto.getDataExame());
        return exameRealizadoMapper.toDto(exameRealizado);
    }
    
}
