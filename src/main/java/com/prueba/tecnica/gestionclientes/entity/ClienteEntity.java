package com.prueba.tecnica.gestionclientes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_completo", nullable = false, length = 50)
    private String nombreCompleto;
    @Column(name = "nro_documento", length = 8, nullable = false, unique = true)
    private String nroDocumento;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 15)
    private String celular;
    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoClienteEntity tipoCliente;
}
