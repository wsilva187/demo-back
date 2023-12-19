package br.soc.avaliacao.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponseDTO {

    private UUID id;

    private String nome;

    private List<ExameRealizadoResponseDTO> examesRealizados;

}
