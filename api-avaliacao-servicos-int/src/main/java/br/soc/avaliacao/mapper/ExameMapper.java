package br.soc.avaliacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.soc.avaliacao.domain.Exame;
import br.soc.avaliacao.dto.ExameRequestDTO;
import br.soc.avaliacao.dto.ExameResponseDTO;
import br.soc.avaliacao.dto.RelatorioExameResponseDTO;
import br.soc.avaliacao.services.vo.ExameVO;

@Mapper(componentModel = "spring")
public abstract class ExameMapper {

    public abstract ExameResponseDTO toDto(Exame entity);

    public abstract RelatorioExameResponseDTO toRelDto(Exame entity);

    @Mapping(target = "id", ignore = true)
    public abstract ExameVO toVO(ExameRequestDTO dto);
    
}
