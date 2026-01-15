package com.prueba.tecnica.gestionclientes.service;

import com.prueba.tecnica.gestionclientes.dto.ClienteDTO;
import com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO;
import com.prueba.tecnica.gestionclientes.dto.TotalPorTipoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {
    Page<ClienteResponseDTO> buscarClientesPorFiltro(String nombre, String nroDocumento, Integer tipoCliente, int page, int size);
    ClienteResponseDTO buscarClientePorId(Integer id);
    void registrarCliente(ClienteDTO dto);
    void actualizarTipoCliente(Integer id, Integer idTipo);
    ClienteResponseDTO actualizarCliente(Integer idCliente, ClienteDTO dto);
    void eliminarCliente(Integer id);
    List<TotalPorTipoDTO> totalesPorTipo();
}
