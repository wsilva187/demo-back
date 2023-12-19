package br.soc.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioExameResponseDTO {
    
    private Long id;
    private String nome;
    private Long total;
    
}
