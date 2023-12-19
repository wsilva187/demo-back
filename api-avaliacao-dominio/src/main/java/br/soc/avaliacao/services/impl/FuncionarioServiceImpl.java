package br.soc.avaliacao.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.NotFoundException;
import br.soc.avaliacao.commons.exception.RepositoryException;
import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.repository.FuncionarioRepository;
import br.soc.avaliacao.services.CustomMessageSource;
import br.soc.avaliacao.services.FuncionarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repository;
    private final CustomMessageSource message;

    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) throws ApiException {
        try {
            return repository.save(funcionario);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.funcionario.salvar"));
        }
    }

    @Override
    public void deletarFuncionario(Funcionario funcionario) throws ApiException {
        try {
            repository.delete(funcionario);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.funcionario.deletar"), e);
        }
    }

    @Override
    public Funcionario buscarPorUuid(UUID idFuncionario) throws ApiException {
        return repository.findByIdExterno(idFuncionario).orElseThrow(() -> new NotFoundException(message.getMessage("erro.funcionario.notFound")));
    }

    @Override
    public Page<Funcionario> buscarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void atualizarFuncionario(Funcionario funcionario, String nome) throws ApiException {
        try {
            funcionario.setNome(nome);
            repository.save(funcionario);
        } catch (Exception e) {
            throw new RepositoryException(message.getMessage("erro.funcionario.atualizar"));
        }
        
    }

}
