package br.soc.avaliacao.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.ValidacaoException;
import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.repository.ExameRealizadoRepository;
import br.soc.avaliacao.services.CustomMessageSource;

@ExtendWith(MockitoExtension.class)
public class ExameRealizadoServiceImplTest {

    @Mock
    private  ExameRealizadoRepository  repository;
    @Mock
    private CustomMessageSource message;
     @InjectMocks
    private  ExameRealizadoServiceImpl service;



    @Test
    void testAddExame() throws ApiException { 
        Funcionario  funcionario = Funcionario.builder().id(32L).nome("Watson").build();
        Exame exame = Exame.builder().id(31L).nome("Urina").build();
        LocalDateTime data = LocalDateTime.now();
        ExameRealizado exameRealizado = ExameRealizado.builder().id(33L).exame(exame).funcionario(funcionario).dataCadastro(data).build();
        Mockito.when(repository.findByFuncionarioAndExameAndDataRealizacao(funcionario, exame, data)).thenReturn(Optional.of(exameRealizado));
        Mockito.when(message.getMessage(anyString(),any())).thenReturn("Teste123");

        try {
         service.addExame(funcionario, exame, data);   
        } catch (ValidacaoException e) {
            Assertions.assertEquals(e.getMessage(), "Teste123");
        }
    }
}