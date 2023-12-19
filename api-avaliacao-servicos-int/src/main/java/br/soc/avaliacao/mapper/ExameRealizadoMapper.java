package br.soc.avaliacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.dto.ExameRealizadoResponseDTO;

@Mapper(componentModel = "spring", uses =  FuncionarioMapper.class)
public abstract class ExameRealizadoMapper {
    
    @Mapping(target = "id", source = "idExterno")
    @Mapping(target = "nomeExame", source = "exame.nome")
    @Mapping(target = "nomeFuncionario", source = "funcionario.nome")
    public abstract ExameRealizadoResponseDTO toDto(ExameRealizado entity);

}
