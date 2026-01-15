package com.prueba.tecnica.gestionclientes.service;

import com.prueba.tecnica.gestionclientes.dto.ClienteDTO;
import com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> buscarClientesPorFiltro(String nombre, String nroDocumento, Integer tipoCliente);
    ClienteResponseDTO buscarClientePorId(Integer id);
    void registrarCliente(ClienteDTO dto);
    void actualizarTipoCliente(Integer id, Integer idTipo);
    ClienteResponseDTO actualizarCliente(Integer idCliente, ClienteDTO dto);
    void eliminarCliente(Integer id);
}
