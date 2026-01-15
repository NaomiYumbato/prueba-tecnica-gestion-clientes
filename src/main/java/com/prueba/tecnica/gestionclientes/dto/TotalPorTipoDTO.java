package com.prueba.tecnica.gestionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TotalPorTipoDTO {
    private String tipoCliente;
    private Long total;
}
