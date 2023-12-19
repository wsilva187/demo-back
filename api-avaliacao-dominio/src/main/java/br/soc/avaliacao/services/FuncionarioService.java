package br.soc.avaliacao.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.domain.Funcionario;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Funcionario funcionario) throws ApiException;

    void deletarFuncionario(Funcionario funcionario)  throws ApiException;

    Funcionario buscarPorUuid(UUID idFuncionario) throws ApiException;

    Page<Funcionario> buscarPaginado(Pageable pageable);

    void atualizarFuncionario(Funcionario funcionario, String nome) throws ApiException;
    
}
