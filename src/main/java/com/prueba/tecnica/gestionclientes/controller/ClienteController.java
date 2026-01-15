package com.prueba.tecnica.gestionclientes.controller;

import com.prueba.tecnica.gestionclientes.dto.ClienteDTO;
import com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO;
import com.prueba.tecnica.gestionclientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<?> buscarClientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String nroDocumento,
            @RequestParam(required = false) Integer tipoCliente
    ) {
        List<ClienteResponseDTO> listClientes = clienteService.buscarClientesPorFiltro(nombre, nroDocumento, tipoCliente);

        if (listClientes.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("mensaje", "No se encontraron clientes"));
        }
        return ResponseEntity.ok(listClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientes(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registrarCliente(@Valid @RequestBody ClienteDTO dto) {
        Map<String, String> response = new HashMap<>();
        try {
            clienteService.registrarCliente(dto);
            response.put("mensaje", "Cliente registrado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}/{idTipo}")
    public ResponseEntity<Map<String, String>> actualizarTipoCliente(
            @PathVariable Integer id,
            @PathVariable Integer idTipo) {
        clienteService.actualizarTipoCliente(id, idTipo);
        return ResponseEntity.ok(Map.of("mensaje", "Tipo de cliente actualizado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteDTO dto) {
        Map<String, String> response = new HashMap<>();
        try {
            ClienteResponseDTO clienteActualizado = clienteService.actualizarCliente(id, dto);
            response.put("mensaje", "Cliente actualizado correctamente");
            response.put("clienteActualizado", clienteActualizado.toString());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(
            @PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            clienteService.eliminarCliente(id);
            response.put("mensaje", "Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }
}
