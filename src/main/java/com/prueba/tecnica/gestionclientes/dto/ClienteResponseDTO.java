package com.prueba.tecnica.gestionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ClienteResponseDTO {
    private String nombreCompleto;
    private String nroDocumento;
    private String email;
    private String celular;
    private String tipoClienteNombre;
}
