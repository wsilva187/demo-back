package br.soc.avaliacao.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameRealizadoResponseDTO {
    
    private UUID id;

    private String nomeFuncionario;

    private String nomeExame;

    private LocalDateTime dataRealizacao;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

}
