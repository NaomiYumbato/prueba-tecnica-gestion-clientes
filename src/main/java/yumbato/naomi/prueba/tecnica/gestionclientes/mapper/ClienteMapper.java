package yumbato.naomi.prueba.tecnica.gestionclientes.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import yumbato.naomi.prueba.tecnica.gestionclientes.entity.ClienteEntity;
import yumbato.naomi.prueba.tecnica.gestionclientes.model.BuscarClientesPorFiltro200Response;
import yumbato.naomi.prueba.tecnica.gestionclientes.model.ClienteRequestDTO;
import yumbato.naomi.prueba.tecnica.gestionclientes.model.ClienteResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);


    @Mapping(source = "tipoCliente.descripcion", target= "tipoCliente")
    ClienteResponseDTO toDto (ClienteEntity c);

    List<ClienteResponseDTO> toDtoList(List<ClienteEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoCliente", ignore = true)
    void updateEntityFromDto(
            ClienteRequestDTO dto,
            @MappingTarget ClienteEntity entity
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoCliente", ignore = true)
    ClienteEntity toEntity(ClienteRequestDTO dto);

    default BuscarClientesPorFiltro200Response toBuscarClientesResponse(Page<ClienteEntity> page) {
        BuscarClientesPorFiltro200Response response =
                new BuscarClientesPorFiltro200Response();

        response.setContent(toDtoList(page.getContent()));
        response.setTotalElements((int) page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setNumber(page.getNumber());
        response.setSize(page.getSize());

        return response;
    }
}
