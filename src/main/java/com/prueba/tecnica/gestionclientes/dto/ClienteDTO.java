package com.prueba.tecnica.gestionclientes.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteDTO {
    @NotNull(message = "El nombre completo es obligatorio")
    private String nombreCompleto;
    @NotNull(message = "El número de documento es obligatorio")
    private String nroDocumento;
    @NotNull(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    private String email;
    @NotNull(message = "El celular es obligatorio")
    private String celular;
    @NotNull(message = "El tipo de cliente es obligatorio")
    private Integer tipoCliente;
}
