package br.soc.avaliacao.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameRequestDTO {

    @NotNull(message = "Nome é obrigatório")
    private String nome;
    
}
