package com.prueba.tecnica.gestionclientes.service.impl;

import com.prueba.tecnica.gestionclientes.dto.ClienteDTO;
import com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO;
import com.prueba.tecnica.gestionclientes.entity.ClienteEntity;
import com.prueba.tecnica.gestionclientes.entity.TipoClienteEntity;
import com.prueba.tecnica.gestionclientes.repository.ClienteRepository;
import com.prueba.tecnica.gestionclientes.repository.TipoClienteRepository;
import com.prueba.tecnica.gestionclientes.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;
    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
    }
    @Override
    public List<ClienteResponseDTO> buscarClientesPorFiltro(String nombre, String nroDocumento, Integer tipoCliente) {
        return clienteRepository.buscarClientesPorFiltro(nombre, nroDocumento, tipoCliente);
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return new ClienteResponseDTO(
                cliente.getNombreCompleto(),
                cliente.getNroDocumento(),
                cliente.getEmail(),
                cliente.getCelular(),
                cliente.getTipoCliente().getDescripcion()
        );
    }

    @Override
    @Transactional
    public void registrarCliente(ClienteDTO dto) {
        if (clienteRepository.existsByNroDocumento(dto.getNroDocumento())) {
            throw new RuntimeException("El DNI escrito ya está registrado");
        }

        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setNroDocumento(dto.getNroDocumento());
        cliente.setEmail(dto.getEmail());
        cliente.setCelular(dto.getCelular());

        TipoClienteEntity tipo = tipoClienteRepository.findById(dto.getTipoCliente())
                .orElseThrow(() -> new RuntimeException("TipoCliente no encontrado"));
        cliente.setTipoCliente(tipo);
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void actualizarTipoCliente(Integer id, Integer idTipo) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        TipoClienteEntity tipo = tipoClienteRepository.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado"));

        cliente.setTipoCliente(tipo);
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO actualizarCliente(Integer idCliente, ClienteDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        TipoClienteEntity tipo = tipoClienteRepository.findById(dto.getTipoCliente())
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado"));

        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setNroDocumento(dto.getNroDocumento());
        cliente.setEmail(dto.getEmail());
        cliente.setCelular(dto.getCelular());
        cliente.setTipoCliente(tipo);

        clienteRepository.save(cliente);
        return new ClienteResponseDTO(
                cliente.getNombreCompleto(),
                cliente.getNroDocumento(),
                cliente.getEmail(),
                cliente.getCelular(),
                tipo.getDescripcion()
        );
    }

    @Override
    @Transactional
    public void eliminarCliente(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
    }
}
