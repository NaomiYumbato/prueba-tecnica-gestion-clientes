package yumbato.naomi.prueba.tecnica.gestionclientes.service;


import yumbato.naomi.prueba.tecnica.gestionclientes.model.*;

import java.util.List;


public interface ClienteService {
    BuscarClientesPorFiltro200Response buscarClientesPorFiltro(String nombre, String nroDocumento, Integer tipoCliente, int page, int size);
    ClienteResponseDTO buscarClientePorId(Integer id);
    ClienteResponseDTO registrarCliente(ClienteRequestDTO dto);
    ClienteResponseDTO actualizarTipoCliente(Integer id, Integer idTipo);
    ClienteResponseDTO actualizarCliente(Integer idCliente, ClienteRequestDTO dto);
    EliminarClientePorId200Response eliminarCliente(Integer id);
    List<TotalPorTipoDTO> totalesPorTipo();
}
