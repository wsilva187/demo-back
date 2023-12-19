package br.soc.avaliacao.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class Teste {


    @Test
    void teste() {
        log.info(""+LocalDate.now().atTime(LocalTime.MAX));
        
    }

}
