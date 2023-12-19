package br.soc.avaliacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.soc.avaliacao.domain.Funcionario;
import br.soc.avaliacao.dto.FuncionarioRequestDTO;
import br.soc.avaliacao.dto.FuncionarioResponseDTO;
import br.soc.avaliacao.services.vo.FuncionarioVO;

@Mapper(componentModel = "spring", uses = { ExameRealizadoMapper.class })
public abstract class FuncionarioMapper {

    @Mapping(target = "id", ignore = true)
    public abstract FuncionarioVO toVO(FuncionarioRequestDTO dto);

    @Mapping(target = "id", source = "idExterno")
    public abstract FuncionarioResponseDTO toDto(Funcionario entity);
    
}
