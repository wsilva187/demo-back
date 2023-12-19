package br.soc.avaliacao.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.dto.ExameRequestDTO;
import br.soc.avaliacao.dto.ExameResponseDTO;
import br.soc.avaliacao.facade.Facade;
import br.soc.avaliacao.mapper.ExameMapper;
import br.soc.avaliacao.rest.ExameRestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exames")
@CrossOrigin(origins = "*")
public class ExameController implements ExameRestService {

    private final Facade facade;
    private final ExameMapper mapper;

    @Override
    @SneakyThrows
    public ExameResponseDTO createExame(ExameRequestDTO dto) {
        Exame  exame = facade.salvarExame(dto.getNome());
        return mapper.toDto(exame);
    }

    @Override
    @SneakyThrows
    public ExameResponseDTO getExameById(Long id) {
        Exame  exame = facade.buscarExamePorId(id);
        return mapper.toDto(exame);
    }

    @Override
    @SneakyThrows
    public void updateExame(Long id, ExameRequestDTO exameDto) {
        facade.atualizarExame(id, mapper.toVO(exameDto));
    }

    @Override
    @SneakyThrows
    public void deleteExame(Long id) {
        facade.deletarExame(id);
    }

    @Override
    @SneakyThrows
    public Page<ExameResponseDTO> buscarPaginado(Pageable pageable) {
        Page<Exame> page = facade.buscarExamePaginado(pageable);
        return page.map(mapper::toDto);
    }
}
