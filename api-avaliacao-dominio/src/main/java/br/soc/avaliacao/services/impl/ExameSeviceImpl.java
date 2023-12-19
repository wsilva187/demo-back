package br.soc.avaliacao.services.impl;

import static br.soc.avaliacao.commons.util.AssertsUtil.assertNotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.NotFoundException;
import br.soc.avaliacao.commons.exception.RepositoryException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.repository.ExameRepository;
import br.soc.avaliacao.services.CustomMessageSource;
import br.soc.avaliacao.services.ExameSevice;
import br.soc.avaliacao.services.vo.ExameVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExameSeviceImpl implements ExameSevice {

    private final ExameRepository repository;
    private final CustomMessageSource message;

    @Override
    public boolean hasExameCadastrado(String nome) {
        return repository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public Exame salvarExame(Exame exame) throws ApiException {
        try {
            return repository.save(exame);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.exame.salvar"));
        }
    }

    @Override
    public Exame buscarPorId(Long id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(message.getMessage("erro.exame.notFound")));
    }

    @Override
    public void atualizarExame(Exame exame, ExameVO exameForm) throws ApiException {
        try {
            exame.setNome(exameForm.getNome());
            repository.save(exame);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.exame.salvar"));
        }
    }

    @Override
    public void deletarExame(Exame exame) throws ApiException {
        assertNotEmpty(exame.getExamesRealizados(), message.getMessage("erro.exame.contemSolicitacao"));
        try {
            repository.delete(exame);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.exame.deletar"));
        }
    }

    @Override
    public Page<Exame> buscarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Exame> findTop(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        return repository.findTop(dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX), pageable);
    }
    
}
