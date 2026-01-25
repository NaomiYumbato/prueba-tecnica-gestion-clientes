package yumbato.naomi.prueba.tecnica.gestionclientes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yumbato.naomi.prueba.tecnica.gestionclientes.api.ApiApi;
import yumbato.naomi.prueba.tecnica.gestionclientes.model.*;
import yumbato.naomi.prueba.tecnica.gestionclientes.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping
public class ClienteController implements ApiApi {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<BuscarClientesPorFiltro200Response> buscarClientesPorFiltro(
            String nombre,
            String nroDocumento,
            Integer tipoCliente,
            Integer page,
            Integer size
    ) {
        BuscarClientesPorFiltro200Response pageResult =
                clienteService.buscarClientesPorFiltro(nombre, nroDocumento, tipoCliente, page, size);
        return ResponseEntity.ok(pageResult);
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(Integer id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> registrarCliente(ClienteRequestDTO clienteRequestDTO){
        return ResponseEntity.status(201).body(clienteService.registrarCliente(clienteRequestDTO));
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> actualizarTipoCliente(Integer id, Integer idTipo){
        return ResponseEntity.ok(clienteService.actualizarTipoCliente(id, idTipo));
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> actualizarClientePorId(Integer id, ClienteRequestDTO clienteRequestDTO){
        return ResponseEntity.ok(clienteService.actualizarCliente(id, clienteRequestDTO));
    }

    @Override
    public ResponseEntity<EliminarClientePorId200Response> eliminarClientePorId(Integer id){
        return ResponseEntity.ok(clienteService.eliminarCliente(id));
    }

    @Override
    public ResponseEntity<List<TotalPorTipoDTO>> totalesPorTipo(){
        return ResponseEntity.ok(clienteService.totalesPorTipo());
    }
}
