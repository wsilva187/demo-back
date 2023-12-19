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
public class ExameRealizadoRequestDTO {
    
    private UUID idFuncionario;
    private Long idExame;
    private LocalDateTime dataExame;

    
}
