package yumbato.naomi.prueba.tecnica.gestionclientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipo_cliente")
public class TipoClienteEntity  {
    @Id
    private Integer id;
    @Column(nullable = false, length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "tipoCliente")
    private List<ClienteEntity> clientes;
}
