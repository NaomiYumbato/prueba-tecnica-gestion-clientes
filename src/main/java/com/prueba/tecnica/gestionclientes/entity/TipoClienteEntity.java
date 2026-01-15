package com.prueba.tecnica.gestionclientes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipo_cliente")
public class TipoClienteEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @Column(nullable = false, length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "tipoCliente")
    private List<ClienteEntity> clientes;
}
