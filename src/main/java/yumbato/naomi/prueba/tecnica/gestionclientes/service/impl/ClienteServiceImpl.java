package yumbato.naomi.prueba.tecnica.gestionclientes.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yumbato.naomi.prueba.tecnica.gestionclientes.entity.ClienteEntity;
import yumbato.naomi.prueba.tecnica.gestionclientes.entity.TipoClienteEntity;
import yumbato.naomi.prueba.tecnica.gestionclientes.exception.BusinessException;
import yumbato.naomi.prueba.tecnica.gestionclientes.exception.ResourceNotFoundException;
import yumbato.naomi.prueba.tecnica.gestionclientes.mapper.ClienteMapper;
import yumbato.naomi.prueba.tecnica.gestionclientes.model.*;
import yumbato.naomi.prueba.tecnica.gestionclientes.repository.ClienteRepository;
import yumbato.naomi.prueba.tecnica.gestionclientes.repository.TipoClienteRepository;
import yumbato.naomi.prueba.tecnica.gestionclientes.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;
    private final ClienteMapper clienteMapper;
    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public BuscarClientesPorFiltro200Response buscarClientesPorFiltro(String nombre, String nroDocumento, Integer tipoCliente, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClienteEntity> pageResponse = clienteRepository.buscarClientesPorFiltro(nombre, nroDocumento, tipoCliente, pageable);
        return clienteMapper.toBuscarClientesResponse(pageResponse);
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente con id " + id + " no encontrado"
                ));
        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO registrarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsByNroDocumento(dto.getNroDocumento())) {
            throw new BusinessException("El DNI escrito ya está registrado");
        }
        TipoClienteEntity tipo = tipoClienteRepository.findById(dto.getTipoCliente())
                .orElseThrow(() ->
                        new ResourceNotFoundException("TipoCliente no encontrado")
                );
        ClienteEntity cliente = clienteMapper.toEntity(dto);
        cliente.setTipoCliente(tipo);
        clienteRepository.save(cliente);

        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO actualizarTipoCliente(Integer id, Integer idTipo) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente con id " + id + " no encontrado"
                ));

        TipoClienteEntity tipo = tipoClienteRepository.findById(idTipo)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de cliente no encontrado"));
        cliente.setTipoCliente(tipo);

        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO actualizarCliente(Integer idCliente, ClienteRequestDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (clienteRepository.existsByNroDocumentoAndIdNot(dto.getNroDocumento(), idCliente)) {
            throw new BusinessException("El DNI escrito ya está registrado");
        }

        clienteMapper.updateEntityFromDto(dto, cliente);

        TipoClienteEntity tipo = tipoClienteRepository.findById(dto.getTipoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de cliente no encontrado"));
        cliente.setTipoCliente(tipo);

        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public EliminarClientePorId200Response eliminarCliente(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);

        EliminarClientePorId200Response response = new EliminarClientePorId200Response();
        response.setId(cliente.getId());

        return response;
    }

    @Override
    public List<TotalPorTipoDTO> totalesPorTipo() {
        List<Object[]> resultados = clienteRepository.obtenerTotalesPorTipo();

        return resultados.stream()
                .map(r -> new TotalPorTipoDTO(
                        (String) r[0],
                        ((Number) r[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
